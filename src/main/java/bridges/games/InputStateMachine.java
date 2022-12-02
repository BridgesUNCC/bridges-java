package bridges.games;
import java.util.function.Supplier;


class InputStateMachine {
    enum CycleState {
	JUST_PRESSED,
	STILL_PRESSED,
	JUST_NOT_PRESSED,
	STILL_NOT_PRESSED
    };

     enum FireState {
	 FIRE,
	 COOLDOWN,
	 NOT_PRESSED,
     }
     
    CycleState s;
     Supplier<Boolean> rawState;

     FireState f;
     int cooldown;
     int cooldownCounter;
     
     public InputStateMachine(Supplier<Boolean> rawState) {
	 s = CycleState.STILL_NOT_PRESSED;
	 this.rawState = rawState;
	 cooldown = 30;
	 f = FireState.NOT_PRESSED;
     }
     
     public void update() {
	 boolean currentState = rawState.get();
	 switch(s) {
	 case JUST_PRESSED:
	     if (currentState) {
		 s = CycleState.STILL_PRESSED;
	     } else {
		 s = CycleState.JUST_NOT_PRESSED;
	     }	     
	     break;
	 case STILL_PRESSED:
	     if (currentState) {
		 s = CycleState.STILL_PRESSED;
	     } else {
		 s = CycleState.JUST_NOT_PRESSED;
	     }	     
	     break;
	 case JUST_NOT_PRESSED:
	     if (currentState) {
		 s = CycleState.JUST_PRESSED;
	     } else {
		 s = CycleState.STILL_NOT_PRESSED;
	     }	     
	     break;
	 case STILL_NOT_PRESSED:
	     if (currentState) {
		 s = CycleState.JUST_PRESSED;
	     } else {
		 s = CycleState.STILL_NOT_PRESSED;
	     }	     
	     break;
	 }

	 switch (f) {
	 case FIRE:
	     if (currentState) {
		 f = FireState.COOLDOWN;
		 cooldownCounter = cooldown;
	     } else {
		 f = FireState.NOT_PRESSED;
	     }	     
	     break;
	 case COOLDOWN:
	     if (currentState) {
		 cooldownCounter --;
		 if (cooldownCounter == 0)
		     f = FireState.FIRE;
	     } else {
		 f = FireState.NOT_PRESSED;
	     }	     
	     break;
	 case NOT_PRESSED:
	     if (currentState) {
		 f = FireState.FIRE;
	     } else {
		 f = FireState.NOT_PRESSED;
	     }	     
	     break;
	 }
	 
     }

     public boolean justPressed() {
	 return s == CycleState.JUST_PRESSED;
     }
     public boolean stillPressed() {
	 return s == CycleState.STILL_PRESSED;
     }
     public boolean justNotPressed() {
	 return s == CycleState.JUST_NOT_PRESSED;
     }
     public boolean stillNotPressed() {
	 return s == CycleState.STILL_NOT_PRESSED;
     }


     public void setFireCooldown(int c) {
	 if (c <= 0) {
	     throw new IllegalArgumentException("cooldown must be positive");
	 }
	 cooldown = c;
     }
     public boolean fire() {
	 return f == FireState.FIRE;
     }

 }
