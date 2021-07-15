package application;
/*
 * Модель соревнования. Содержит всю необходимую информацию в соответствии с БД
 */
public class Comp {
	int idCompetition;
    String name;
    String date;
    String Reward_name;
    
	public int getIdCompetition() {
		return idCompetition;
	}
	public String getName() {
		return name;
	}
	public String getDate() {
		return date;
	}
	public String getReward_name() {
		return Reward_name;
	}
	public void setIdCompetition(int idCompetition) {
		this.idCompetition = idCompetition;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setReward_name(String reward_name) {
		Reward_name = reward_name;
	}
	public Comp(int idCompetition, String name, String date, String reward_name) {
		super();
		this.idCompetition = idCompetition;
		this.name = name;
		this.date = date;
		Reward_name = reward_name;
	}
}
