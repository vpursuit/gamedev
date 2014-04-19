/*
 *  Copyright (c) 2008 Peter Trebing. All rights reserved.
 *
 *  Any unauthorised copying, duplication, reproduction and
 *  compilation will constitute an infringement of copyright.
 *
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  AS IS AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 *  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.anschalter;

import de.anschalter.linalg.Vector3;
import de.anschalter.linalg.VectorMath;

/**
 * 
 * @author keeper
 */
public class Particle {

	/**
	 * @file
	 * 
	 *       This file contains the definitions for the paticle class, which can
	 *       be used in place of rigid bodies for simpler simulations or
	 *       assemblies. A particle is the simplest object that can be simulated
	 *       in the physics system.
	 * 
	 * 
	 *       It has position data (no orientation data), along with velocity. It
	 *       can be integrated forward through time, and have linear forces, and
	 *       impulses applied to it. The particle manages its state and allows
	 *       access through a set of methods.
	 */
	/**
	 * @name Characteristic Data and State
	 * 
	 *       This data holds the state of the particle. There are two sets of
	 *       data: characteristics and state.
	 * 
	 *       Characteristics are properties of the particle independent of its
	 *       current kinematic situation. This includes mass, moment of inertia
	 *       and damping properties. Two identical particles will have the same
	 *       values for their characteristics.
	 * 
	 *       State includes all the characteristics and also includes the
	 *       kinematic situation of the particle in the current simulation. By
	 *       setting the whole state data, a particle's exact game state can be
	 *       replicated. Note that state does not include any forces applied to
	 *       the body. Two identical rigid bodies in the same simulation will
	 *       not share the same state values.
	 * 
	 *       The state values make up the smallest set of independent data for
	 *       the particle. Other state data is calculated from their current
	 *       values. When state data is changed the dependent values need to be
	 *       updated: this can be achieved either by integrating the simulation,
	 *       or by calling the calculateInternals function. This two stage
	 *       process is used because recalculating internals can be a costly
	 *       process: all state changes should be carried out at the same time,
	 *       allowing for a single call.
	 * 
	 * @see calculateInternals
	 */
	/**
	 * Holds the inverse of the mass of the particle. It is more useful to hold
	 * the inverse mass because integration is simpler, and because in real time
	 * simulation it is more useful to have objects with infinite mass
	 * (immovable) than zero mass (completely unstable in numerical simulation).
	 */
	float inverseMass;
	/**
	 * Holds the amount of damping applied to linear motion. Damping is required
	 * to remove energy added through numerical instability in the integrator.
	 */
	float damping;
	/**
	 * Holds the linear position of the particle in world space.
	 */
	Vector3 position;
	/**
	 * Holds the linear velocity of the particle in world space.
	 */
	Vector3 velocity;
	/**
	 * @name Force Accumulators
	 * 
	 *       These data members store the current force and global linear
	 *       acceleration of the particle. Holds the accumulated force to be
	 *       applied at the next simulation iteration only. This value is zeroed
	 *       at each integration step.
	 */
	Vector3 forceAccum = new Vector3(0, 0, 0);
	/**
	 * Holds the acceleration of the particle. This value can be used to set
	 * acceleration due to gravity (its primary use), or any other constant
	 * acceleration.
	 */
	Vector3 acceleration;

	/**
	 * These functions are used to simulate the particle's motion over time. A
	 * normal application sets up one or more rigid bodies, applies permanent
	 * forces (i.e. gravity), then adds transient forces each frame, and
	 * integrates, prior to rendering.
	 * 
	 * Currently the only integration function provided is the first order
	 * Newton Euler method. Integrates the particle forward in time by the given
	 * amount. This function uses a Newton-Euler integration method, which is a
	 * linear approximation to the correct integral. For this reason it may be
	 * inaccurate in some cases.
	 */
	void integrate(float duration) {

		// We don't integrate things with zero mass.
		if (inverseMass <= 0.0f) {
			return;
		}

		// Update linear position.
		position.addScaledVector(velocity, duration);

		// Work out the acceleration from the force
		Vector3 resultingAcc = acceleration;
		resultingAcc.addScaledVector(forceAccum, inverseMass);

		// Update linear velocity from the acceleration.
		velocity.addScaledVector(resultingAcc, duration);

		// Impose drag.
		velocity.scale(damping);
		// velocity.scale((float) Math.pow(damping, duration));

		// Clear the forces.
		clearAccumulator();

	}

	/**
	 * Sets the mass of the particle.
	 * 
	 * @param mass
	 *            The new mass of the body. This may not be zero. Small masses
	 *            can produce unstable rigid bodies under simulation.
	 * 
	 * @warning This invalidates internal data for the particle. Either an
	 *          integration function, or the calculateInternals function should
	 *          be called before trying to get any settings from the particle.
	 */
	void setMass(final float mass) {
		inverseMass = ((float) 1.0) / mass;
	}

	/**
	 * Gets the mass of the particle.
	 * 
	 * @return The current mass of the particle.
	 */
	float getMass() {

		if (inverseMass == 0) {
			return Float.MAX_VALUE;
		} else {
			return ((float) 1.0) / inverseMass;
		}

	}

	/**
	 * Sets the inverse mass of the particle.
	 * 
	 * @param inverseMass
	 *            The new inverse mass of the body. This may be zero, for a body
	 *            with infinite mass (i.e. unmovable).
	 * 
	 * @warning This invalidates internal data for the particle. Either an
	 *          integration function, or the calculateInternals function should
	 *          be called before trying to get any settings from the particle.
	 */
	public void setInverseMass(final float inverseMass) {
		this.inverseMass = inverseMass;
	}

	/**
	 * Gets the inverse mass of the particle.
	 * 
	 * @return The current inverse mass of the particle.
	 */
	public float getInverseMass() {
		return inverseMass;
	}

	/**
	 * Returns true if the mass of the particle is not-infinite.
	 * 
	 * @return true if mass is finit
	 */
	public boolean hasFiniteMass() {
		return (inverseMass >= 0.0f);
	}

	/**
	 * Sets both the damping of the particle.
	 */
	void setDamping(final float damping) {
		this.damping = damping;
	}

	/**
	 * Gets the current damping value.
	 */
	float getDamping() {
		return damping;
	}

	/**
	 * Sets the position of the particle.
	 * 
	 * @param position
	 *            The new position of the particle.
	 */
	void setPosition(final Vector3 position) {
		this.position = position;
	}

	/**
	 * Sets the position of the particle by component.
	 * 
	 * @param x
	 *            The x coordinate of the new position of the rigid body.
	 * 
	 * @param y
	 *            The y coordinate of the new position of the rigid body.
	 * 
	 * @param z
	 *            The z coordinate of the new position of the rigid body.
	 */
	void setPosition(final float x, final float y, final float z) {
		position = new Vector3(x, y, z);
	}

	/**
	 * Fills the given vector with the position of the particle.
	 * 
	 * @param position
	 *            A pointer to a vector into which to write the position.
	 */
	void getPosition(Vector3 position) {
		VectorMath.copyVector(this.position, position);
	}

	/**
	 * Gets the position of the particle.
	 * 
	 * @return The position of the particle.
	 */
	Vector3 getPosition() {
		return position;
	}

	/**
	 * Sets the velocity of the particle.
	 * 
	 * @param velocity
	 *            The new velocity of the particle.
	 */
	void setVelocity(final Vector3 velocity) {
		this.velocity = velocity;
	}

	/**
	 * Sets the velocity of the particle by component.
	 * 
	 * @param x
	 *            The x coordinate of the new velocity of the rigid body.
	 * 
	 * @param y
	 *            The y coordinate of the new velocity of the rigid body.
	 * 
	 * @param z
	 *            The z coordinate of the new velocity of the rigid body.
	 */
	void setVelocity(final float x, final float y, final float z) {
		velocity = new Vector3(x, y, z);
	}

	/**
	 * Fills the given vector with the velocity of the particle.
	 * 
	 * @param velocity
	 *            A pointer to a vector into which to write the velocity. The
	 *            velocity is given in world local space.
	 */
	void getVelocity(Vector3 velocity) {
		VectorMath.copyVector(this.velocity, velocity);
	}

	/**
	 * Gets the velocity of the particle.
	 * 
	 * @return The velocity of the particle. The velocity is given in world
	 *         local space.
	 */
	public Vector3 getVelocity() {
		return velocity;
	}

	/**
	 * Sets the constant acceleration of the particle.
	 * 
	 * @param acceleration
	 *            The new acceleration of the particle.
	 */
	public void setAcceleration(final Vector3 acceleration) {
		this.acceleration = acceleration;
	}

	/**
	 * Sets the constant acceleration of the particle by component.
	 * 
	 * @param x
	 *            The x coordinate of the new acceleration of the rigid body.
	 * 
	 * @param y
	 *            The y coordinate of the new acceleration of the rigid body.
	 * 
	 * @param z
	 *            The z coordinate of the new acceleration of the rigid body.
	 */
	void setAcceleration(final float x, final float y, final float z) {
		acceleration = new Vector3(x, y, z);
	}

	/**
	 * Fills the given vector with the acceleration of the particle.
	 * 
	 * @param acceleration
	 *            A pointer to a vector into which to write the acceleration.
	 *            The acceleration is given in world local space.
	 */
	void getAcceleration(Vector3 acceleration) {
		VectorMath.copyVector(this.acceleration, acceleration);
	}

	/**
	 * Gets the acceleration of the particle.
	 * 
	 * @return The acceleration of the particle. The acceleration is given in
	 *         world local space.
	 */
	public Vector3 getAcceleration() {
		return acceleration;
	}

	/**
	 * Clears the forces applied to the particle. This will be called
	 * automatically after each integration step.
	 */
	void clearAccumulator() {
		forceAccum.clear();
	}

	/**
	 * Adds the given force to the particle, to be applied at the next iteration
	 * only.
	 * 
	 * @param force
	 *            The force to apply.
	 */
	void addForce(final Vector3 force) {
		forceAccum.add(force);
	}
}