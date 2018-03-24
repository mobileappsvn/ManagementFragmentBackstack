package robert.com.unittest;

import android.text.TextUtils;

import java.io.File;

public class ChatMessage {
	public static final String WINK = "WINK";
	public static final String PP = "PP";
	public static final String AUDIO = "AUDIO";
	public static final String VIDEO = "VIDEO";
	public static final String PHOTO = "PHOTO";
	public static final String FILE = "FILE";
	public static final String GIFT = "GIFT";
	public static final String LOCATION = "LCT";
	public static final String STICKER = "STK";
	public static final String STARTVIDEO = "SVIDEO";
	public static final String ENDVIDEO = "EVIDEO";
	public static final String STARTVOICE = "SVOICE";
	public static final String ENDVOICE = "EVOICE";
	public static final String CMD = "CMD";
	public static final String TYPING = "TYPING";
    public static final String CALLREQUEST = "CALLREQ" ;

    // Call request setting the same iOs
    public static final String CALLREQUEST_VOICE = "voip_voice";
    public static final String CALLREQUEST_VIDEO = "voip_video";

	public static final int VoIPActionNone = 0;
	public static final int VoIPActionVoiceStart = 1;
	public static final int VoIPActionVoiceEnd = 2;
	public static final int VoIPActionVoiceEndNoAnswer = 3;
	public static final int VoIPActionVoiceEndBusy = 4;
	public static final int VoIPActionVideoStart = 5;
	public static final int VoIPActionVideoEnd = 6;
	public static final int VoIPActionVideoEndNoAnswer = 7;
	public static final int VoIPActionVideoEndBusy = 8;

	protected boolean msgContentStartsWithMsgId;
	protected boolean msgContentContainsMsgId;
	protected String messageId = "";
	protected String userId;
	protected boolean isOwn;
	protected String content;
	protected String msgType;
	private boolean msgSecured;
	protected File fileMessage;

	public static final long TIMEOUT = 20 * 60 * 1000;
	protected String timeStamp;
    protected String readTime = "";
	protected boolean isHeader = false;
	protected boolean isAddHiddenToFavourite = false;
	protected boolean isFileDelete = false;
	protected boolean isUnlock = false;

	protected long timeInMilisecond = 0;
	protected boolean isEnoughPointToSend = true;
	private int is_free;

	// status of sent message
	protected int statusSend = 0;//StatusConstant.STATUS_SUCCESS;


	private int saveSecurityPosition = -1;
	public boolean isEndOfList = false;

	public ChatMessage() {
	}

	/**
	 * Use when this is message received
	 * 
	 * @param userId
	 * @param isOwn
	 * @param content
	 * @param timeStamp
	 *            must in GMT
	 * @param msgType
	 */
	public ChatMessage(String messageId, String userId, boolean isOwn, String content, String timeStamp, String msgType, boolean msgSecured) {
		super();
		this.messageId = messageId;
		this.userId = userId;
		this.isOwn = isOwn;
		this.content = content;
		this.timeStamp = Utility.convertGMTtoLocale(timeStamp);
		timeInMilisecond = Utility.convertTimeToMilisecond(this.timeStamp);
		this.msgType = msgType;
		this.msgSecured = msgSecured;
	}

	/**
	 * Use when this is message sent
	 * 
	 * @param userId
	 * @param isOwn
	 * @param content
	 * @param timeStamp
	 *            must in GMT
	 * @param msgType
	 */
	public ChatMessage(String userId, boolean isOwn, String content,
			String timeStamp, String msgType) {
		super();
		this.messageId = "";
		this.userId = userId;
		this.isOwn = isOwn;
		this.content = content;
		this.timeStamp = Utility.convertGMTtoLocale(timeStamp);
		timeInMilisecond = Utility.convertTimeToMilisecond(this.timeStamp);
		this.msgType = msgType;
	}

	/**
	 * Use when this is message received
	 * 
	 * @param userId
	 * @param isOwn
	 * @param content
	 * @param timeStamp
	 *            must in GMT
	 * @param fileMessage
	 */
	public ChatMessage(String messageId, String userId, boolean isOwn,
			String content, String timeStamp, String msgType,
			File fileMessage) {
		this(messageId, userId, isOwn, content, timeStamp, msgType, false);
		this.fileMessage = fileMessage;
	}

	/**
	 * User when parse message from history
	 * 
	 * @param msgId
	 * @param userId
	 * @param isOwn
	 * @param timeStamp
	 * @param msgType
	 * @param fileMessage
	 */
	public ChatMessage(String msgId, String userId, boolean isOwn, String timeStamp, String msgType, File fileMessage, boolean msgSecured) {
		this(userId, isOwn, null, timeStamp, msgType);
		this.fileMessage = fileMessage;
		this.messageId = msgId;
		this.msgSecured = msgSecured;
	}

	/**
	 * Use for video, video, photo when this is message sent
	 *
	 * @param userId
	 * @param isOwn
	 * @param timeStamp
	 * @param msgType
	 * @param fileMessage
	 */
	public ChatMessage(String userId, boolean isOwn, String timeStamp, String msgType, File fileMessage, int noCharge) {
		this(userId, isOwn, null, timeStamp, msgType);
		this.fileMessage = fileMessage;
		this.is_free = noCharge;
	}

	/**
	 * Use for video, video, photo when this is message sent
	 * 
	 * @param userId
	 * @param isOwn
	 * @param timeStamp
	 * @param msgType
	 * @param fileMessage
	 */
	public ChatMessage(String userId, boolean isOwn, String timeStamp,
			String msgType, File fileMessage) {
		this(userId, isOwn, null, timeStamp, msgType);
		this.fileMessage = fileMessage;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public long getTimeInMilisecond() {
		return timeInMilisecond;
	}

	public void setTimeInMilisecond(long timeInMilisecond) {
		if (!isHeader) {
			return;
		}
		this.timeInMilisecond = timeInMilisecond;
	}

	public void setHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}

	public boolean isHeader() {
		return isHeader;
	}

	public void setAddHiddenFavourite(boolean value) {
		this.isAddHiddenToFavourite = value;
	}

	public boolean isAddHiddenFavourite() {
		return this.isAddHiddenToFavourite;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isOwn() {
		return isOwn;
	}

	public void setOwn(boolean isOwn) {
		this.isOwn = isOwn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		//this.content = StringUtils.replaceAll(content, "&amp;", "&");
	}


	public boolean isMsgSecured() {
		return msgSecured;
	}

	public void setMsgSecured(boolean msgSecured) {
		this.msgSecured = msgSecured;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

    public String getReadTime() {
        return readTime;
    }

	/**
	 * set timeStamp for message
	 * 
	 * @param timeStamp
	 *            must in Locale time
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
		timeInMilisecond = Utility.convertTimeToMilisecond(this.timeStamp);
	}

    public void setReadTime(String readTime) {
        if (!TextUtils.isEmpty(readTime)) {
            this.readTime = Utility.convertReadTimeGMTtoLocale(readTime);
        } else {
            this.readTime = "";
        }
    }

	public String getMessageToSend() {
		return Utility.encryptMessageToSend(content);
	}

	public void decryptMessageHistory() {
		this.content = Utility.decryptMessageReceived(content);
	}

	public String getMessageReceived() {
		if (content == null) {
			return null;
		}
		return content;
	}

	public File getFileMessage() {
		return fileMessage;
	}

	public void setFileMessage(File fileMessage) {
		this.fileMessage = fileMessage;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public static ChatMessage makeGiftMessage(String userId, boolean owner,
			String giftId, String time) {
		ChatMessage chatMessage = new ChatMessage(userId, owner, giftId
				+ "| | ", time, ChatMessage.GIFT);
		return chatMessage;
	}

	public void setEnoughPointToSend(boolean isEnoughPointToSend) {
		this.isEnoughPointToSend = isEnoughPointToSend;
	}

	public boolean isEnoughPointToSend() {
		return isEnoughPointToSend;
	}

	public boolean isUploadSuccess() {
		/*if (fileMessage != null && fileMessage.getFileId() != null)
			return true;
		return fileMessage.uploadState == UploadState.SUCCESSFUL ? true : false;*/
		return true;
	}

	public boolean isFileMessage() {
		return msgType == PHOTO || msgType == VIDEO || msgType == AUDIO ? true
				: false;
	}

	public boolean isTimeOutForFileMessage() {
		long time = System.currentTimeMillis() - timeInMilisecond;
		return time >= TIMEOUT;
	}

	public boolean isTypingMessage() {
		return msgType == TYPING;
	}

	public void setStatusSend(int statusSend) {
		this.statusSend = statusSend;
	}

	public int getStatusSend() {
		return this.statusSend;
	}

	public boolean isFileDelete() {
		return isFileDelete;
	}

	public void setIsFileDelete(boolean isFileDelete) {
		this.isFileDelete = isFileDelete;
	}

	public boolean isUnlock() {
		return isUnlock;
	}

	public void setUnlock(boolean unlock) {
		isUnlock = unlock;
	}


	public int getSaveSecurityPosition() {
		return saveSecurityPosition;
	}

	public void setSaveSecurityPosition(int saveSecurityPosition) {
		this.saveSecurityPosition = saveSecurityPosition;
	}


	public int getIs_free() {
		return is_free;
	}

	public void setIs_free(int is_free) {
		this.is_free = is_free;
	}


	@Override
    public String toString() {
        return "ChatMessage [messageId=" + messageId + ", userId=" + userId
                + ", isOwn=" + isOwn + ", content=" + content + ", msgType="
                + msgType + ", fileMessage=" + fileMessage + ", timeStamp="
                + timeStamp + ", readTime=" + readTime + ", isHeader="
                + isHeader + ", isAddHiddenToFavourite="
                + isAddHiddenToFavourite + ", timeInMilisecond="
                + timeInMilisecond + ", isEnoughPointToSend="
                + isEnoughPointToSend + ", statusSend=" + statusSend
				+ ", is_free=" + is_free + "]";
    }

}
