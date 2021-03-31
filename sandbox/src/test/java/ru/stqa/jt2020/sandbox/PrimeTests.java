package ru.stqa.jt2020.sandbox;


import org.junit.Assert;
import org.testng.annotations.Test;

public class PrimeTests {



    @Test(priority = 10)
    public void testPrimes() {
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test(priority = 11)
    public void testPrimesFast() {
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }

    @Test(priority = 11)
    public void testPrimesSQRT() {
        Assert.assertTrue(Primes.isPrimeSQRT(Integer.MAX_VALUE));
    }

    @Test(priority = 13)
    public void testNonPrimes() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE -2 ));
    }

    @Test(priority = 14)
    public void testPrimesWhile(){
        Assert.assertTrue((Primes.isPrimeWhile(Integer.MAX_VALUE)));
    }

    @Test(priority = 16 ,enabled = false)
    public void testPrimesLong(){
        long l = Integer.MAX_VALUE;
        Assert.assertTrue((Primes.isPrime(l)));
    }
}
