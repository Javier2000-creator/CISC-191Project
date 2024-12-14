 
/**
 * TimerInterface.java 
 * 
 * Purpose: Adds method signatures that can be used for GameTimer that can be 
 * flexible for other classes as well.
 * 
 * Features:
 * - Adds three method signatures: one for starting the timer, the other for stopping it,
 * and the other for resetting the timer.
 * 
 * Author: Christopher OShea & Javier Ayala
 * Date: 10 December 2024
 */
public interface TimerInterface
{
	void start();
	void stop();
	void reset(int newTime);
}
