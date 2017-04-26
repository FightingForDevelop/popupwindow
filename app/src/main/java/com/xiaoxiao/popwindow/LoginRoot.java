package com.xiaoxiao.popwindow;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginRoot implements Serializable {

	/**
	 * VersionNumber : 2.4.6
	 * return : 1
	 * employeecode :
	 * portalname :
	 * message : 用户名密码错误
	 * imageurl :
	 * auth : c365d6c4446b4e0681d4553a2add0d8a21ff58110d9b4439a344280eef218e8c
	 * crmusername :
	 * IsVersion : True
	 * Version : null
	 * BuildNumber : 174
	 * build : null
	 * FileUrl : null
	 * result : 1
	 * code : 0
	 * msg : null
	 * data : null
	 * facePermission : True
	 * voiceAssistant : False
	 * IsNeedSign : false
	 * IsForceSign : false
	 * EmployeeCode_Honest : null
	 */

	private String VersionNumber;//版本号
	@SerializedName("return")
	private String returnX;
	private String employeecode;
	private String portalname;//真实姓名
	private String message;//信息
	private String imageurl;
	private String auth;
	private String crmusername;
	private String IsVersion;
	private String Version;
	private String BuildNumber;
	private String build;
	private String FileUrl;
	private String result;//结果
	private int code;
	private String msg;
	private String data;
	private String facePermission;
	private String voiceAssistant;
	private boolean IsNeedSign;
	private boolean IsForceSign;
	private String EmployeeCode_Honest;

	public String getUrlHeadImage() {
		return urlHeadImage;
	}

	public void setUrlHeadImage(String urlHeadImage) {
		this.urlHeadImage = urlHeadImage;
	}

	private String urlHeadImage;

	public String getVersionNumber() {
		return VersionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		VersionNumber = versionNumber;
	}

	public String getReturnX() {
		return returnX;
	}

	public void setReturnX(String returnX) {
		this.returnX = returnX;
	}

	public String getEmployeecode() {
		return employeecode;
	}

	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode;
	}

	public String getPortalname() {
		return portalname;
	}

	public void setPortalname(String portalname) {
		this.portalname = portalname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getCrmusername() {
		return crmusername;
	}

	public void setCrmusername(String crmusername) {
		this.crmusername = crmusername;
	}

	public String getIsVersion() {
		return IsVersion;
	}

	public void setIsVersion(String isVersion) {
		IsVersion = isVersion;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getBuildNumber() {
		return BuildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		BuildNumber = buildNumber;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getFileUrl() {
		return FileUrl;
	}

	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFacePermission() {
		return facePermission;
	}

	public void setFacePermission(String facePermission) {
		this.facePermission = facePermission;
	}

	public String getVoiceAssistant() {
		return voiceAssistant;
	}

	public void setVoiceAssistant(String voiceAssistant) {
		this.voiceAssistant = voiceAssistant;
	}

	public boolean isNeedSign() {
		return IsNeedSign;
	}

	public void setNeedSign(boolean needSign) {
		IsNeedSign = needSign;
	}

	public boolean isForceSign() {
		return IsForceSign;
	}

	public void setForceSign(boolean forceSign) {
		IsForceSign = forceSign;
	}

	public String getEmployeeCode_Honest() {
		return EmployeeCode_Honest;
	}

	public void setEmployeeCode_Honest(String employeeCode_Honest) {
		EmployeeCode_Honest = employeeCode_Honest;
	}

}
