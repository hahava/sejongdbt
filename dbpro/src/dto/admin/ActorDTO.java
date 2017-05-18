package dto.admin;


public class ActorDTO implements DTO {
	public String actorCode;
	public String actorName;
	public String actorGender;
	public String actorBirth;

	public ActorDTO(String actorCode, String actorName, String actorGender, String actorBirth) {
		this.actorCode = actorCode;
		this.actorName = actorName;
		this.actorGender = actorGender;
		this.actorBirth = actorBirth;
	}

	@Override
	public String toString() {
		return actorName + "\t" + actorBirth + "\t" + actorGender + "\t" + actorCode;
	}
}
