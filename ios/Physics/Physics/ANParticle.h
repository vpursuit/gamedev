//
//  Particle.h
//  Physics
//
//  Created by Peter Trebing on 06.06.12.
//  Copyright (c) 2012 anschalter.de. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol ANParticle <NSObject>

@property float* position;
@property float* velocity;
@property float* acceleration;
@property float* force;

@property float inverseMass;
@property float damping;


-(id)initWithPosition:(float*)p andVelocity:(float*)v andAcceleration:(float*)a andForce:(float*)f andMass:(float)m;

-(void)integrate:(float) duration;

-(void)clearForce;

-(void)addForce:(float*) f;

-(BOOL)hasFiniteMass;

@end
