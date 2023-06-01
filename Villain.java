package model.world;

import java.util.ArrayList;

public class Villain extends Champion {

	public Villain(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	
	public String toString() {
		return "\n" + super.toString();
		
	}
	
	public String toStringCurr() {
		return "\n Villain" + super.toStringCurr();
		
	}
	
	public String toStringRem() {
		return "\n Villain"  + super.toStringRem();
		
	}
	
	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion c : targets) {

			c.setCurrentHP(0);

		}

	}

}
