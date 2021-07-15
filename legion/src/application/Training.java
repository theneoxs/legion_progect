package application;
/*
 * Модель тренировки. Содержит всю необходимую информацию в соответствии с БД
 */
public class Training {
	int Group_idGroup;
    String date;
    int Train_idTrain;
    String time;
    int idTrain;
    String name;
    String descriptior;
	public Training(int group_idGroup, String date, int train_idTrain, String time, int idTrain, String name,
			String descriptior) {
		super();
		Group_idGroup = group_idGroup;
		this.date = date;
		Train_idTrain = train_idTrain;
		this.time = time;
		this.idTrain = idTrain;
		this.name = name;
		this.descriptior = descriptior;
	}
	public int getGroup_idGroup() {
		return Group_idGroup;
	}
	public String getDate() {
		return date;
	}
	public int getTrain_idTrain() {
		return Train_idTrain;
	}
	public String getTime() {
		return time;
	}
	public int getIdTrain() {
		return idTrain;
	}
	public String getName() {
		return name;
	}
	public String getDescriptior() {
		return descriptior;
	}
	public void setGroup_idGroup(int group_idGroup) {
		Group_idGroup = group_idGroup;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTrain_idTrain(int train_idTrain) {
		Train_idTrain = train_idTrain;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setIdTrain(int idTrain) {
		this.idTrain = idTrain;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescriptior(String descriptior) {
		this.descriptior = descriptior;
	}
    
}
