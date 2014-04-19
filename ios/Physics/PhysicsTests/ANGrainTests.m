//
//  PhysicsTests.m
//  PhysicsTests
//
//  Created by Peter Trebing on 12.05.12.
//  Copyright (c) 2012 anschalter.de. All rights reserved.
//

#import "ANGrainTests.h"
#include "ANDefaultParticle.h"

@implementation ANGrainTests
{
    float p[3*1024] __attribute__ ((aligned));
    float a[3*1024] __attribute__ ((aligned));
    float v[3*1024] __attribute__ ((aligned));
    float f[3*1024] __attribute__ ((aligned)); 
    
}

- (void)setUp
{
    [super setUp];
    
    // Set-up code here.
}

- (void)tearDown
{
    // Tear-down code here.
    
    [super tearDown];
}

- (void)testInit
{
    ANDefaultParticle *particle = [[ANDefaultParticle alloc] init];
    STAssertNotNil(particle, @"Could not create particel with default constructor");
}

- (void)testInitWith
{
    float porg[3] = {1.0f, 2.0f, 3.0f};
    float aorg[3] = {0.0f, 0.0f, 0.0f};
    float vorg[3] = {5.0f, 6.0f, 7.0f};
    float forg[3] = {5.0f, 6.0f, 7.0f};
    
    p[0] = porg[0]; p[1] = porg[1]; p[2] = porg[2];
    a[0] = aorg[0]; a[1] = aorg[1]; a[2] = aorg[2];
    v[0] = vorg[0]; v[1] = vorg[1]; v[2] = vorg[2];
    f[0] = forg[0]; f[1] = forg[1]; f[2] = forg[2];
    float m = 10.0f;
    
    ANDefaultParticle *particle = [[ANDefaultParticle alloc] initWithPosition:p andVelocity:v andAcceleration:a andForce:(float*)f andMass:m];
    
    STAssertNotNil(particle, @"Could not create particel");
    
    float duration = 0.0f; // no integration necesary
    
    float* pos = [particle position];
    STAssertTrue(pos[0] == porg[0] + duration * v[0], [NSString stringWithFormat:@"unexpected pos[0]=%f", pos[0]]);
    STAssertTrue(pos[1] == porg[1] + duration * v[1], [NSString stringWithFormat:@"unexpected pos[0]=%f", pos[1]]);
    STAssertTrue(pos[2] == porg[2] + duration * v[2], [NSString stringWithFormat:@"unexpected pos[0]=%f", pos[2]]);
    
    float* vel = [particle velocity];
    STAssertTrue(vel[0] == vorg[0], [NSString stringWithFormat:@"unexpected vel[0]=%f", vel[0]]);
    STAssertTrue(vel[1] == vorg[1], [NSString stringWithFormat:@"unexpected vel[1]=%f", vel[1]]);
    STAssertTrue(vel[2] == vorg[2], [NSString stringWithFormat:@"unexpected vel[2]=%f", vel[2]]);
    
    float* acc = [particle acceleration];
    STAssertTrue(acc[0] == aorg[0], [NSString stringWithFormat:@"unexpected acc[0]=%f", acc[0]]);
    STAssertTrue(acc[1] == aorg[1], [NSString stringWithFormat:@"unexpected acc[0]=%f", acc[1]]);
    STAssertTrue(acc[2] == aorg[2], [NSString stringWithFormat:@"unexpected acc[0]=%f", acc[2]]);
    
    float* frc = [particle force];
    STAssertTrue(frc[0] == forg[0], [NSString stringWithFormat:@"unexpected frc[0]=%f", frc[0]]);
    STAssertTrue(frc[1] == forg[1], [NSString stringWithFormat:@"unexpected frc[0]=%f", frc[1]]);
    STAssertTrue(frc[2] == forg[2], [NSString stringWithFormat:@"unexpected frc[0]=%f", frc[2]]);    
    
    float inverseMass = [particle inverseMass];
    STAssertTrue(inverseMass == 1/10.0f, @"x inverseMass not as expected");    
}

- (void)testIntegrate
{
    
    float porg[3] = {1.0f, 2.0f, 3.0f};
    float aorg[3] = {0.0f, 0.0f, 0.0f};
    float vorg[3] = {5.0f, 6.0f, 7.0f};
    float forg[3] = {5.0f, 6.0f, 7.0f};
    
    p[0] = porg[0]; p[1] = porg[1]; p[2] = porg[2];
    a[0] = aorg[0]; a[1] = aorg[1]; a[2] = aorg[2];
    v[0] = vorg[0]; v[1] = vorg[1]; v[2] = vorg[2];
    f[0] = forg[0]; f[1] = forg[1]; f[2] = forg[2];
    float m = 10.0f;
    
    ANDefaultParticle *particle = [[ANDefaultParticle alloc] initWithPosition:p andVelocity:v andAcceleration:a  andForce:(float*)f andMass:m];
    
    float duration = 1.0f;
    
    [particle integrate:duration];    
    STAssertNotNil(particle, @"Could not create particel");
    
    float* pos = [particle position];
    STAssertTrue(pos[0] == porg[0] + duration * v[0], [NSString stringWithFormat:@"unexpected pos[0]=%f", pos[0]]);
    STAssertTrue(pos[1] == porg[1] + duration * v[1], [NSString stringWithFormat:@"unexpected pos[0]=%f", pos[1]]);
    STAssertTrue(pos[2] == porg[2] + duration * v[2], [NSString stringWithFormat:@"unexpected pos[0]=%f", pos[2]]);
    
    float* vel = [particle velocity];
    STAssertTrue(vel[0] == vorg[0], [NSString stringWithFormat:@"unexpected vel[0]=%f", vel[0]]);
    STAssertTrue(vel[1] == vorg[1], [NSString stringWithFormat:@"unexpected vel[1]=%f", vel[1]]);
    STAssertTrue(vel[2] == vorg[2], [NSString stringWithFormat:@"unexpected vel[2]=%f", vel[2]]);
    
    float* acc = [particle acceleration];
    STAssertTrue(acc[0] == aorg[0], [NSString stringWithFormat:@"unexpected acc[0]=%f", acc[0]]);
    STAssertTrue(acc[1] == aorg[1], [NSString stringWithFormat:@"unexpected acc[0]=%f", acc[1]]);
    STAssertTrue(acc[2] == aorg[2], [NSString stringWithFormat:@"unexpected acc[0]=%f", acc[2]]);

    float* frc = [particle force];
    STAssertTrue(frc[0] == forg[0], [NSString stringWithFormat:@"unexpected frc[0]=%f", frc[0]]);
    STAssertTrue(frc[1] == forg[1], [NSString stringWithFormat:@"unexpected frc[0]=%f", frc[1]]);
    STAssertTrue(frc[2] == forg[2], [NSString stringWithFormat:@"unexpected frc[0]=%f", frc[2]]);    
    
    float inverseMass = [particle inverseMass];
    STAssertTrue(inverseMass == 1/10.0f, @"x inverseMass not as expected");
}

@end
