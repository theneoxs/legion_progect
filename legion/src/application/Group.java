package application;
/*
 * Модель группы. Содержит всю необходимую информацию в соответствии с БД
 */
public class Group {
	int idGroup;
    int number;
    String description;
    int Coach_idCoach;
	public Group(int idGroup, int number, String description, int coach_idCoach) {
		super();
		this.idGroup = idGroup;
		this.number = number;
		this.description = description;
		Coach_idCoach = coach_idCoach;
	}
	public int getIdGroup() {
		return idGroup;
	}
	public int getNumber() {
		return number;
	}
	public String getDescription() {
		return description;
	}
	public int getCoach_idCoach() {
		return Coach_idCoach;
	}
	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCoach_idCoach(int coach_idCoach) {
		Coach_idCoach = coach_idCoach;
	}
    
}
