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

import java.text.MessageFormat;

/**
 * 
 * @author keeper
 */
public class TimeSpan implements Comparable<TimeSpan> {

	public static final long TICKS_PER_MILLISECOND = 1000000L;
	public static final long TICKS_PER_SECOND = 1000 * TICKS_PER_MILLISECOND;
	public static final long TICKS_PER_MINUTE = 60 * TICKS_PER_SECOND;
	public static final long TICKS_PER_HOUR = 60 * TICKS_PER_MINUTE;
	public static final long TICKS_PER_DAY = 24 * TICKS_PER_HOUR;
	public static final TimeSpan MAX_VALUE = new TimeSpan(Long.MAX_VALUE);
	public static final TimeSpan MIN_VALUE = new TimeSpan(Long.MIN_VALUE);
	public static final TimeSpan ZERO = new TimeSpan(0);
	/**
	 * 
	 * Private members
	 */
	private long ticks = 0;
	private long days = 0;
	private long hours = 0;
	private long minutes = 0;
	private long seconds = 0;
	private long milliSeconds = 0;

	//
	// Zusammenfassung:
	// Initializes a new System.TimeSpan to the specified number of ticks.
	//
	// Parameter:
	// ticks:
	// A time period expressed in 100-nanosecond units.
	public TimeSpan(long ticks) {

		if (ticks <= TICKS_PER_MINUTE) {
			initFast(ticks);
		} else {

			long tmp = 0;
			if (ticks > TICKS_PER_DAY) {
				this.days = ticks / TICKS_PER_DAY;

				tmp = ticks % TICKS_PER_DAY;
			}

			if (tmp > TICKS_PER_HOUR) {

				this.hours = tmp / TICKS_PER_HOUR;

				tmp = ticks % TICKS_PER_HOUR;
			}

			initFast(tmp);

		}
	}

	//
	// Zusammenfassung:
	// Initializes a new System.TimeSpan to a specified number of hours,
	// minutes,
	// and seconds.
	//
	// Parameter:
	// hours:
	// Number of hours.
	//
	// minutes:
	// Number of minutes.
	//
	// seconds:
	// Number of seconds.
	//
	// Ausnahmen:
	// IllegalArgumentException
	// The parameters specify a System.TimeSpan value less than
	// System.TimeSpan.MinValue
	// or greater than System.TimeSpan.MaxValue.
	public TimeSpan(int hours, int minutes, int seconds)
			throws IllegalArgumentException {
		this.days = 0;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		normalize();
	}

	//
	// Zusammenfassung:
	// Initializes a new System.TimeSpan to a specified number of days, hours,
	// minutes,
	// and seconds.
	//
	// Parameter:
	// days:
	// Number of days.
	//
	// hours:
	// Number of hours.
	//
	// minutes:
	// Number of minutes.
	//
	// seconds:
	// Number of seconds.
	//
	// Ausnahmen:
	// System.ArgumentOutOfRangeException:
	// The parameters specify a System.TimeSpan value less than
	// System.TimeSpan.MinValue
	// or greater than System.TimeSpan.MaxValue.

	public TimeSpan(int days, int hours, int minutes, int seconds)
			throws IllegalArgumentException {
		this(days, hours, minutes, seconds, 0);
	}

	//
	// Zusammenfassung:
	// Initializes a new System.TimeSpan to a specified number of days, hours,
	// minutes,
	// seconds, and milliseconds.
	//
	// Parameter:
	// days:
	// Number of days.
	//
	// hours:
	// Number of hours.
	//
	// minutes:
	// Number of minutes.
	//
	// seconds:
	// Number of seconds.
	//
	// milliseconds:
	// Number of milliseconds.
	//
	// Ausnahmen:
	// System.ArgumentOutOfRangeException:
	// The parameters specify a System.TimeSpan value less than
	// System.TimeSpan.MinValue
	// or greater than System.TimeSpan.MaxValue.

	public TimeSpan(int days, int hours, int minutes, int seconds,
			int milliseconds) throws IllegalArgumentException {
		this.days = days;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.milliSeconds = milliseconds;
		normalize();
	}

	private TimeSpan(long days, long hours, long minutes, long seconds,
			long milliseconds) throws IllegalArgumentException {
		this.days = days;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.milliSeconds = milliseconds;
		normalize();
	}

	public TimeSpan add(long ticks) {
		this.ticks += ticks;
		return normalize();
	}

	public TimeSpan add(TimeSpan timeSpan) {
		this.ticks += timeSpan.ticks;
		this.days += timeSpan.days;
		this.hours += timeSpan.hours;
		this.minutes += timeSpan.minutes;
		this.seconds += timeSpan.seconds;
		this.milliSeconds += timeSpan.milliSeconds;
		return normalize();
	}

	private TimeSpan normalize() {
		if (ticks >= TICKS_PER_MILLISECOND) {
			milliSeconds += ticks / TICKS_PER_MILLISECOND;
			ticks = ticks % TICKS_PER_MILLISECOND;
		}
		if (milliSeconds >= 1000) {
			seconds += milliSeconds / 1000;
			milliSeconds = milliSeconds % 1000;
		}
		if (seconds >= 60) {
			minutes += seconds / 60;
			seconds = seconds % 60;
		}
		if (minutes >= 60) {
			hours += minutes / 60;
			minutes = minutes % 60;
		}
		if (hours > 24) {
			days += hours / 24;
			hours = hours % 24;
		}
		return this;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (int) (days ^ (days >>> 32));
		result = 31 * result + (int) (hours ^ (hours >>> 32));
		result = 31 * result + (int) (milliSeconds ^ (milliSeconds >>> 32));
		result = 31 * result + (int) (minutes ^ (minutes >>> 32));
		result = 31 * result + (int) (seconds ^ (seconds >>> 32));
		result = 31 * result + (int) (ticks ^ (ticks >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TimeSpan)) {
			return false;
		}
		TimeSpan other = (TimeSpan) obj;

		return (this.days == other.days && this.hours == other.hours
				&& this.milliSeconds == other.milliSeconds
				&& this.minutes == other.minutes
				&& this.seconds == other.seconds && this.ticks == other.ticks);

	}

	@Override
	public int compareTo(TimeSpan o) {
		long tmp = days - o.getDays();
		if (tmp < 0) {
			return -1;
		} else if (tmp > 0) {
			return 1;
		} else {
			tmp = hours - o.getHours();
			if (tmp < 0) {
				return -1;
			} else if (tmp > 0) {
				return 1;
			} else {
				tmp = minutes - o.getMinutes();
				if (tmp < 0) {
					return -1;
				} else if (tmp > 0) {
					return 1;
				} else {
					tmp = seconds - o.getSeconds();
					if (tmp < 0) {
						return -1;
					} else if (tmp > 0) {
						return 1;
					} else {
						tmp = milliSeconds - o.getMilliSeconds();
						if (tmp < 0) {
							return -1;
						} else if (tmp > 0) {
							return 1;
						} else {

							tmp = ticks - o.getTicks();
							if (tmp < 0) {
								return -1;
							} else if (tmp > 0) {
								return 1;
							} else {
								return 0;
							}
						}
					}
				}
			}
		}
	}

	long getTicks() {
		return ticks;
	}

	public int getDays() {
		return (int) days;
	}

	public int getHours() {
		return (int) hours;
	}

	public int getMinutes() {
		return (int) minutes;
	}

	public int getSeconds() {
		return (int) seconds;
	}

	public int getMilliSeconds() {
		return (int) milliSeconds;
	}

	@Override
	public String toString() {
		Object[] o = { Long.valueOf(getDays()), Long.valueOf(getHours()),
				Long.valueOf(getMinutes()), Long.valueOf(getSeconds()),
				Long.valueOf(getMilliSeconds()) };

		return new MessageFormat(
				"{0,number,00}:{1,number,00}: {2,number,00}: {3,number,00}: {4,number,00}")
				.format(o);
	}

	private void initFast(long ticks) {

		long tmp = ticks;

		this.minutes = tmp / TICKS_PER_MINUTE;
		tmp = tmp % TICKS_PER_MINUTE;

		if (tmp >= TICKS_PER_SECOND) {
			this.seconds = tmp / TICKS_PER_SECOND;
			tmp = tmp % TICKS_PER_SECOND;
		}

		if (tmp >= TICKS_PER_MILLISECOND) {
			this.milliSeconds = tmp / TICKS_PER_MILLISECOND;
			tmp = tmp % TICKS_PER_MILLISECOND;
		}

		this.ticks = tmp;
	}
}
