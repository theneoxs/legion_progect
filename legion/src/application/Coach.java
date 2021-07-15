package application;
/*
 * Модель тренера. Содержит всю необходимую информацию в соответствии с БД
 */
public class Coach {
	int idCoach;
    String name;
    String surname;
    String lastname;
    int telephone;
    int age;
    String rank;
	public Coach(int idCoach, String name, String surname, String lastname, int telephone, int age, String rank) {
		super();
		this.idCoach = idCoach;
		this.name = name;
		this.surname = surname;
		this.lastname = lastname;
		this.telephone = telephone;
		this.age = age;
		this.rank = rank;
	}
	public int getIdCoach() {
		return idCoach;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getLastname() {
		return lastname;
	}
	public int getTelephone() {
		return telephone;
	}
	public int getAge() {
		return age;
	}
	public String getRank() {
		return rank;
	}
	public void setIdCoach(int idCoach) {
		this.idCoach = idCoach;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
    
}
