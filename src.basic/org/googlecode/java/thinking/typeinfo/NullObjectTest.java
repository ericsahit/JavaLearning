package org.googlecode.java.thinking.typeinfo;

import java.util.ArrayList;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

//P342
public class NullObjectTest {
	@Test
	public void test() {
		Staff staff=new Staff("CEO", "CTO", "CFO", "Manager" ,"Coder");
		staff.fillPosition("CEO", new Employee("Me", "Last", "Here"));
		if (staff.positionAvailable("Coder"))
			staff.fillPosition("Coder", new Employee("eric", "wong", "there"));
		Print.print(staff);
	}
}
interface Null { }

class Employee {
	public final String first;
	public final String last;
	public final String address;
	
	public Employee(String first, String last, String address) {
		this.first = first;
		this.last = last;
		this.address = address;
	}

	public String toString() {
		return "Person: "+first+" "+last+" "+address;
	}
	
	public static class NullPerson extends Employee implements Null {

		private NullPerson() {
			super("None", "None", "None");
		}
		public String toString() { return "NullPerson"; }
		
	}
	public static final Employee NULL=new NullPerson();
}

class Position {
	public String title;
	public Employee person;
	public Position(String title) {
		this.title = title;
		this.person = Employee.NULL;
	}
	public Position(String title, Employee person) {
		this.title = title;
		this.person = person;
		if (person==null)
			person=Employee.NULL;
	}
	public String toString() {
		return "Position: "+title+" "+person;
	}
}

class Staff extends ArrayList<Position> {
	void add(String title, Employee person) {
		add(new Position(title, person));
	}
	
	void add(String... titles) {
		for (String title : titles)
			add(new Position(title));
	}
	public Staff(String...titles) { add(titles); }
	public boolean positionAvailable(String title) {
		for (Position position : this)
			if (position.title.equals(title))
				return true;
		return false;
	}
	public void fillPosition(String title, Employee hire) {
		for (Position position : this) {
			if (position.title.equals(title) && position.person == Employee.NULL) {
				position.person=hire;
				return;
			}
		}
	}
}


