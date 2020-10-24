package org.liamjd.pi

import com.pi4j.Pi4J
import com.pi4j.io.gpio.digital.DigitalOutput
import com.pi4j.io.gpio.digital.DigitalState
import com.pi4j.plugin.raspberrypi.platform.RaspberryPiPlatform
import kotlin.system.exitProcess

class LightHarp {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "INFO")

            println("Setting library path to /home/pi/PiShare")
            System.setProperty("pi4j.library.path", "/home/pi/PiShare")

            println("Auto constructing pi4J context")
            val pi4J = Pi4J.newAutoContext()

            println("Iterating platforms")
//            pi4J.defaultPlatform<RaspberryPiPlatform>()
            val platforms = pi4J.platforms()
            platforms.describe().print(System.out)

            println("Configuring buzzer 1")
            val pin1OutConfig = DigitalOutput.newConfigBuilder(pi4J)
                .id("buzzer1")
                .name("Buzzer 1")
                .address(24)
                .shutdown(DigitalState.LOW)
                .provider("pigpio-digital-output")

            println("Configuring buzzer 2")
            val pin2OutConfig = DigitalOutput.newConfigBuilder(pi4J)
                .id("buzzer1")
                .name("Buzzer 1")
                .address(25)
                .shutdown(DigitalState.LOW)
                .provider("pigpio-digital-output")

            println("Creating buzzer 1")
            val buzzer1 = pi4J.create(pin1OutConfig)
            println("Creating buzzer 2")
            val buzzer2 = pi4J.create(pin2OutConfig)

            print("Buzzing now")
            buzz(buzzer1,buzzer2)
            println("Finished buzzing")

            pi4J.shutdown()
            exitProcess(1)
        }

        private fun buzz(buzz1: DigitalOutput, buzz2: DigitalOutput) {
            val pitch = 2000
            val duration = 0.1
            val period = 1.0 / pitch
            val period2 = (period / 2).toLong()
            val cycles = (duration * pitch).toInt()

            for (bz in 0..cycles) {
                buzz1.on()
                buzz2.off()
                Thread.sleep(period2)
                buzz1.off()
                buzz2.on()
                Thread.sleep(period2)
            }
        }
    }
}
