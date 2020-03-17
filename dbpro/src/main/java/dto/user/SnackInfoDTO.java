package dto.user;

public class SnackInfoDTO {
	public String SNACK_CODE;
	public String SNACK_NAME;
	public String SNACK_CONTENT;
	public int SNACK_CAL;
	public int SNACK_PRICE;

	public String getSNACK_CODE() {
		return SNACK_CODE;
	}

	public void setSNACK_CODE(String sNACK_CODE) {
		SNACK_CODE = sNACK_CODE;
	}

	public String getSNACK_NAME() {
		return SNACK_NAME;
	}

	public void setSNACK_NAME(String sNACK_NAME) {
		SNACK_NAME = sNACK_NAME;
	}

	public String getSNACK_CONTENT() {
		return SNACK_CONTENT;
	}

	public void setSNACK_CONTENT(String sNACK_CONTENT) {
		SNACK_CONTENT = sNACK_CONTENT;
	}

	public int getSNACK_CAL() {
		return SNACK_CAL;
	}

	public void setSNACK_CAL(int sNACK_CAL) {
		SNACK_CAL = sNACK_CAL;
	}

	public int getSNACK_PRICE() {
		return SNACK_PRICE;
	}

	public void setSNACK_PRICE(int sNACK_PRICE) {
		SNACK_PRICE = sNACK_PRICE;
	}

	@Override
	public String toString() {
		return "SnackInfoDTO [SNACK_CODE=" + SNACK_CODE + ", SNACK_NAME=" + SNACK_NAME + ", SNACK_CONTENT="
				+ SNACK_CONTENT + ", SNACK_CAL=" + SNACK_CAL + ", SNACK_PRICE=" + SNACK_PRICE + "]";
	}

	public SnackInfoDTO(String sNACK_CODE, String sNACK_NAME, String sNACK_CONTENT, int sNACK_CAL, int sNACK_PRICE) {
		SNACK_CODE = sNACK_CODE;
		SNACK_NAME = sNACK_NAME;
		SNACK_CONTENT = sNACK_CONTENT;
		SNACK_CAL = sNACK_CAL;
		SNACK_PRICE = sNACK_PRICE;
	}

}
