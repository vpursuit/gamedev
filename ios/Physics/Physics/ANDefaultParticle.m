//
//  ANDefaultParticle.m
//  Physics
//
//  Created by Peter Trebing on 12.05.12.
//  Copyright (c) 2012 anschalter.de. All rights reserved.
//

#import <Accelerate/Accelerate.h>
#import <math.h>
#import "ANDefaultParticle.h"


@implementation ANDefaultParticle

@synthesize position;
@synthesize acceleration;
@synthesize velocity;
@synthesize force;
@synthesize inverseMass;
@synthesize damping;

-(id) initWithPosition:(float*)pos andVelocity:(float*)v andAcceleration:(float*)a andForce:(float*)f andMass:(float)m {
    self = [super init];
    if (self) {
        position = pos;
        velocity = v;
        acceleration = a;
        force = f;
        inverseMass = 1.0f/m;
        damping = 1.0f;
    }
    return self;
}

- (BOOL) hasFiniteMass {
    return inverseMass >= 0.0f;
}

- (void) setPosition: (float*) pos{
    vDSP_mmov(pos, position, 3, 1, 3, 1); 
}

- (void) setVelocity: (float*) v {
    vDSP_mmov(v, velocity, 3, 1, 3, 1); 
}

- (void) setForce: (float*) f {
    vDSP_mmov(f, force, 3, 1, 3, 1); 
}

- (void) setAcceleration:(float *) a {
    vDSP_mmov(a, acceleration, 3, 1, 3, 1); 
}

- (void) integrate: (float) duration {
    
    // We don't integrate things with zero mass.
	if (inverseMass <= 0.0f) return;
    
    NSAssert(duration > 0.0f, @"Duration must be > 0.0 to integrate");
    
    // Update linear position.
    //position = duration*velocity + position
    printf("p = %f, %f, %f\n", position[0], position[1], position[2]);
    vDSP_vsma(velocity, 1, &duration, position, 1, position, 1, 3);
    printf("p = %f, %f, %f\n", position[0], position[1], position[2]);
    
    // Work out the acceleration from the force
    float accelerationAccu[3];
    printf("a = %f, %f, %f\n", accelerationAccu[0], accelerationAccu[1], accelerationAccu[2]);
    vDSP_mmov(acceleration, accelerationAccu, 3, 1, 3, 1);
    vDSP_vsma(accelerationAccu, 1, &inverseMass, force, 1, force, 1, 3);
    printf("a = %f, %f, %f\n", accelerationAccu[0], accelerationAccu[1], accelerationAccu[2]);
    
    printf("v = %f, %f, %f\n", velocity[0], velocity[1], velocity[2]);
    // Update linear velocity from the acceleration.
    vDSP_vsma(accelerationAccu, 1, &duration, velocity, 1, velocity, 1, 3);
    printf("v = %f, %f, %f\n", velocity[0], velocity[1], velocity[2]);
    
    // Impose drag.
    float d = powf(damping, duration);
    vDSP_vsmul(velocity, 1, &d, velocity, 1, 3);
    printf("v = %f, %f, %f\n", velocity[0], velocity[1], velocity[2]);
        
}

- (void) clearForce {
    vDSP_vclr(force, 1, 3);
}

- (void) addForce: (float*) f {
    vDSP_vadd(f, 1, force, 1, force, 1, 3);
}


@end
