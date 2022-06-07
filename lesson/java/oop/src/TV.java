/**
 * TV객체가 될 클래스 입니다
 * @author 김준용
 * @version 1.0
 */
public class TV {
	private boolean power;
	private int channel;
	private int volume;
	public void powerOn() {
		power = true;
	}
	public boolean isPower() {
		return this.power; //현재 사용중인 TV객체의 power값을 리턴한다
	}
	/**
	 * TV 채널을 설정한다
	 * @param channel 설정할 채널값
	 */
	public void setChannel (int channel) {
		if(channel < 0) {
			System.out.println("잘못된 채널입니다.");
			return;
		}
		this.channel = channel;
	}

	/**
	 * 채널을 반환한다
	 * @return 채널값
	 */
	public int getChannel() {
		return channel;
	}
	/**
	 * 음량을 1증가 시킨다
	 */
	
	public void volumeUp() {
		volume++;
	}
	/**
	 * 음량을 반환한다
	 * @return 음량값
	 */
	public int getVolume() {
		return volume;
	}
	
}
