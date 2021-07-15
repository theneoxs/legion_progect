package application;
/*
 * Модель абонемента. Содержит всю необходимую информацию в соответствии с БД
 */
public class Abonement {
	int idAbonement;
    String date;
    String lastDate;
    int trainCount;
    int Group_idGroup;
    String Paytype_variant;
    int Sportsman_idSportsman;
	public Abonement(int idAbonement, String date, String lastDate, int trainCount, int group_idGroup,
			String paytype_variant, int sportsman_idSportsman) {
		super();
		this.idAbonement = idAbonement;
		this.date = date;
		this.lastDate = lastDate;
		this.trainCount = trainCount;
		Group_idGroup = group_idGroup;
		Paytype_variant = paytype_variant;
		Sportsman_idSportsman = sportsman_idSportsman;
	}
	public int getIdAbonement() {
		return idAbonement;
	}
	public String getDate() {
		return date;
	}
	public String getLastDate() {
		return lastDate;
	}
	public int getTrainCount() {
		return trainCount;
	}
	public int getGroup_idGroup() {
		return Group_idGroup;
	}
	public String getPaytype_variant() {
		return Paytype_variant;
	}
	public int getSportsman_idSportsman() {
		return Sportsman_idSportsman;
	}
	public void setIdAbonement(int idAbonement) {
		this.idAbonement = idAbonement;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public void setTrainCount(int trainCount) {
		this.trainCount = trainCount;
	}
	public void setGroup_idGroup(int group_idGroup) {
		Group_idGroup = group_idGroup;
	}
	public void setPaytype_variant(String paytype_variant) {
		Paytype_variant = paytype_variant;
	}
	public void setSportsman_idSportsman(int sportsman_idSportsman) {
		Sportsman_idSportsman = sportsman_idSportsman;
	}
    
}
