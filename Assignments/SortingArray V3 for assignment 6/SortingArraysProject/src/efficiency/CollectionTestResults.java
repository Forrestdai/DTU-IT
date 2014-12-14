/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package efficiency;

/**
 *
 * @author JamesFoxes
 */
class CollectionTestResults
{
    public long addTime;
    public long getTime;
    public long addFirstTime;
    public long addLastTime;
    
    private int elementsRun;
    private int timesRun = 0;

    public CollectionTestResults(int elementsRun)
    {
        this.elementsRun = elementsRun;
    }
    
    public void incrementTimesRun()
    {
        ++timesRun;
    }
    
    public void printAverageTimes()
    {
        System.out.println("Time: " + ((double) addTime) / (1000000 * timesRun) + "ms to add " + elementsRun + " elements. With an average of: " + getAverageMilisecondTime(addTime) + "ms per element.");

        System.out.println("Time: " + ((double) getTime) / (1000000 * timesRun) + "ms to get " + elementsRun + " elements. With an average of: " + getAverageMilisecondTime(getTime) + "ms per element.");
        
        System.out.println("Time: " + ((double) addFirstTime) / (1000000 * timesRun) + "ms to add " + elementsRun + " elements first. With an average of: " + getAverageMilisecondTime(addFirstTime) + "ms per element.");
        
        System.out.println("Time: " + ((double) addLastTime) / (1000000 * timesRun) + "ms to add " + elementsRun + " elements last. With an average of: " + getAverageMilisecondTime(addLastTime) + "ms per element.");
    }
    
    private double getAverageMilisecondTime(long fullDuration)
    {
        long averageTime = fullDuration / (elementsRun * timesRun);
        double averageTimeMiliseconds = ((double) averageTime) / 1000000;
        return averageTimeMiliseconds;
    }
}
