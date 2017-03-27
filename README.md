# Learning Encryption
* I am learning cryptography by writing various encryption programs.
* I will put all of my projects in this github repository.

## Current Progress
* Caesar Cipher - Finished
  * A [Caesar Cipher](https://en.wikipedia.org/wiki/Caesar_cipher) shifts all letters by a set amount down the alphabet. To decrypt, the receiver shifts all letters backwards by the same amount
  * I built a basic GUI that can encrypt and decrypt by a an arbitrary amount. Test it out by by launching the included .jar file (Encryption/Caesar/out/artifacts/Caesar_jar/Caesar.jar)
  * I also built a Caesar Cipher cracker which works by comparing the number of recognized words in each of the 26 possible shifts of the alphabet

* Data Encryption Standard (DES) - In Progress
  * Early cryptograpic algorithm no longer in widespread use (except as triple-DES) due to insecurity
  * See [implementation](http://page.math.tu-berlin.de/~kant/teaching/hess/krypto-ws2006/des.htm) and [general information](https://en.wikipedia.org/wiki/Data_Encryption_Standard) for details
  * My progess so far has been converting an input string into the sequence of 64 bit blocks required for the DES encryption algorithm