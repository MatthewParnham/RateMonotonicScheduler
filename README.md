# Rate Monotonic Scheduler
A simple program that implements a rate monotonic scheduling algorithm for four threads that run an arbitrary doWork() function.

## Execution
To run, simply execute Run.ps1 if you are on Windows or compile and run Main if you're on Linux.

## Design Description
I began by setting all constant variables in Main so they could be easily changed from a central location.  The scheduler is split into two classes, one to handle scheduling and one to perform the work.  The program begins by creating the scheduler which creates 4 threads and 4 semaphores (one for each thread).  The threads all are scheduled to begin working by releasing their appropriate semaphores.  The scheduler increments time by sleeping for ten milliseconds.  Every unit of time, the scheduler checks if it has reached the period for each thread.  If it has, it releases that thread's semaphore to have it run again.  It also checks if the thread has completed its task by the end of the period.  Every time a thread fails, a counter is incremented to keep track of the overruns.  Also, each thread keeps track of how many times it has run with a counter.  At the end, the threads are joined and the final output it displayed.

## Output
normalCase.txt contains the output for the base program with the given conditions.
overRunCase.txt contains the output when I changed the amount of times thread 2 performs doWork to 10,000,000.
