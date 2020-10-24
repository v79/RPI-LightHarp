# Raspberry Pi LightHarp Test Project

This is an experimental project to recreate the simple [LightHarp](http://monkmakes.com/downloads/pi_box_1a.pdf) project from the MonkMakes Project Box 1.

The original project is written in Python, but I want to recreate it in a JVM language, ideally Kotlin.

## Library

As I am testing this on a Raspberry PI 4 Model B (4Gb RAM), I am attempting to use the [PiJ4 version 2](https://v2.pi4j.com/) library. But I'm not having a lot of luck...

The project is written in Kotlin version 1.4.10, and is using pi4j version 2.0-SNAPSHOT.

It doesn't work. Current error when trying to initialise the Digital Output pins is:

```
[main] WARN com.pi4j.library.pigpio.impl.PiGpioNativeImpl - PIGPIO ERROR: PI_INIT_FAILED; pigpio initialisation failed
Exception in thread "main" java.lang.reflect.InvocationTargetException
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at com.pi4j.provider.impl.ProviderProxyHandler.invoke(ProviderProxyHandler.java:102)
        at com.sun.proxy.$Proxy0.create(Unknown Source)
        at com.pi4j.context.Context.create(Context.java:309)
        at com.pi4j.internal.IOCreator.create(IOCreator.java:60)
        at com.pi4j.internal.IOCreator.create(IOCreator.java:101)
        at com.pi4j.internal.IOCreator.create(IOCreator.java:189)
        at org.liamjd.pi.LightHarp$Companion.main(LightHarp.kt:44)
        at org.liamjd.pi.LightHarp.main(LightHarp.kt)
Caused by: java.io.IOException: PIGPIO ERROR: PI_INIT_FAILED; pigpio initialisation failed
        at com.pi4j.library.pigpio.impl.PiGpioBase.validateResult(PiGpioBase.java:292)
        at com.pi4j.library.pigpio.impl.PiGpioBase.validateResult(PiGpioBase.java:277)
        at com.pi4j.library.pigpio.impl.PiGpioNativeImpl.gpioInitialise(PiGpioNativeImpl.java:101)
        at com.pi4j.library.pigpio.PiGpio.initialize(PiGpio.java:155)
        at com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalOutputProviderImpl.create(PiGpioDigitalOutputProviderImpl.java:62)
        at com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalOutputProviderImpl.create(PiGpioDigitalOutputProviderImpl.java:43)
        ... 12 more
```

