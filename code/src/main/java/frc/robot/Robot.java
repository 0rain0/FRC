/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;

import java.sql.Time;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  final byte stick = 0;
  final byte motor = 1;
  final byte encoder_a = 3;
  final byte encoder_b = 4;
  final byte solenoid = 2;
  final byte compressor = 5;

  Joystick m_stick = new Joystick(stick);
  WPI_TalonSRX intake = new WPI_TalonSRX(motor);
  Encoder m_encoder = new Encoder(encoder_a, encoder_b);
  Solenoid m_solenoid = new Solenoid(solenoid);
  Compressor m_compressor = new Compressor(compressor);
  Timer m_timer = new Timer();
  



  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_solenoid.close();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if(m_stick.getRawButton(2)){
      m_solenoid.set(true);
    }else{
      m_solenoid.set(false);
    }

    if(m_stick.getRawButton(3)){
      m_encoder.reset();
      m_solenoid.set(true);
      m_timer.reset();
      m_timer.start();
      if(m_encoder.getDistance() < 50 && m_timer.get() >= 1){
        intake.set(0.3);
      }else{
        intake.set(0);
      }
    }else{
      intake.set(0);
    }

    if(m_compressor.getPressureSwitchValue()){
      m_compressor.start();
    }else{
      m_compressor.stop();
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }
}
