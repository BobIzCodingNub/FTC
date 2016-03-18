import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class TeleTank extends OpMode{
  //Creating motor variables.
  DcMotor motor1;
  DcMotor motor2;
  
  public TeleTank(){
  
    //Initialize
    public void init(){
      //We assume motor 1 is on the left and 2 is on the right.
      motor1 = hardwareMap.dcMotor.get("motor_1");
      motor2 = hardwareMap.dcMotor.get("motor_2");
      motor2.setDirection(DcMotor.Direction.REVERSE);
    }
    
    //Loop or Update
    public void loop(){
      float left = -gamepad1.left_stick_y;
      float right = -gamepad1.right_stick_y;
		
		  right = Range.clip(right, -1, 1);
		  left = Range.clip(left, -1, 1);
		  
		  right = (float)scaleInput(right);
		  left =  (float)scaleInput(left);
		  
		  motorRight.setPower(right);
		  motorLeft.setPower(left);
    }
    
    public void stop(){
    
    }
    
    double scaleInput(double dVal)  {
		  double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
				0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

		  int index = (int) (dVal * 16.0);
		  
		  if (index < 0) {
			  index = -index;
		  }

		  if (index > 16) {
			  index = 16;
		  }

		  double dScale = 0.0;
		  if (dVal < 0) {
			  dScale = -scaleArray[index];
		  } else {
			  dScale = scaleArray[index];
		  }

		  return dScale;
	  }
  
  }
  
}
