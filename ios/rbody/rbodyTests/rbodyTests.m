//
//  rbodyTests.m
//  rbodyTests
//
//  Created by Peter Trebing on 05.06.12.
//  Copyright (c) 2012 anschalter.de. All rights reserved.
//

#import "rbodyTests.h"
#import "particle.h"

@implementation rbodyTests

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

- (void)testExample
{
    
    cyclone::Particle p = *new cyclone::Particle();
    
    p.integrate(1.0f);
    
    STFail(@"Unit tests are not implemented yet in rbodyTests");
}

@end
