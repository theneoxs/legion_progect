package application;
/*
 * Модель спортсмена. Содержит всю необходимую информацию в соответствии с БД
 */
public class Sportsman {
	int idSportsman;
    String name;
    String surname;
    String lastname;
    int telephone;
    int age;
    int weight;
	public int getIdSportsman() {
		return idSportsman;
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
	public int getWeight() {
		return weight;
	}
	public void setIdSportsman(int idSportsman) {
		this.idSportsman = idSportsman;
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
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Sportsman(int idSportsman, String name, String surname, String lastname, int telephone, int age,
			int weight) {
		super();
		this.idSportsman = idSportsman;
		this.name = name;
		this.surname = surname;
		this.lastname = lastname;
		this.telephone = telephone;
		this.age = age;
		this.weight = weight;
	}

	
    
}
