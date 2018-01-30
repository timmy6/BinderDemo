// IRemoteService.aidl
package qiming.binderdemo;

// Declare any non-default types here with import statements

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int add(int a,int b);

    int getPid();
}
