package com.data.controller.adminController.superAdminController.superAdminReplyAdvice;

import java.io.IOException;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.model.SuperAdminReplyAdviceModel;
import com.data.service.SendEmail;
import com.data.service.adminService.superAdminFunctions.superAdminReplyAdvice.SuperAdminReplyAdviceService;

@RequestMapping("/SuperAdminReplyAdviceController")
@Controller
public class SuperAdminReplyAdviceController {
	@RequestMapping("/softwareAdviceNumber")
	@ResponseBody
	public String getAdviceNumber(
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		if (Account.isEmpty())
		{
			System.out.println("Account:"+Account);
			return null;
		}
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SuperAdminReplyAdviceService superAdminReplyAdviceService = (SuperAdminReplyAdviceService) factory
				.getBean("SuperAdminReplyAdviceService");
		SuperAdminReplyAdviceModel superAdminReplyAdviceModel = new SuperAdminReplyAdviceModel();
		superAdminReplyAdviceModel.setAccount(Account);
		int softwareAdviceNumber = superAdminReplyAdviceService
				.getSoftwareAdviceNumber(superAdminReplyAdviceModel);

		String json = "{\"softwareAdviceNumber\":" + softwareAdviceNumber + "}";
		return json;
	}

	@RequestMapping("/getSoftwareAdvices")
	@ResponseBody
	public JSONArray getAdvices(
			@RequestParam(value = "Account", required = false) String Account)
			throws IOException {
		if (Account.isEmpty())
		{
			System.out.println("Account:"+Account);
			return null;
		}
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SuperAdminReplyAdviceService superAdminReplyAdviceService = (SuperAdminReplyAdviceService) factory
				.getBean("SuperAdminReplyAdviceService");
		SuperAdminReplyAdviceModel superAdminReplyAdviceModel = new SuperAdminReplyAdviceModel();
		superAdminReplyAdviceModel.setAccount(Account);
		JSONArray jsonArray = superAdminReplyAdviceService
				.getSoftwareAdvices(superAdminReplyAdviceModel);

		return jsonArray;
	}

	@RequestMapping("/sendReply")
	@ResponseBody
	public JSONArray sendReply(
			@RequestParam(value = "Account", required = false) String Account,
			@RequestParam(value = "Title", required = false) String Title,
			@RequestParam(value = "Reply", required = false) String Reply,
			@RequestParam(value = "ADid", required = false) String ADid,
			@RequestParam(value = "Vid", required = false) String Vid)
			throws IOException {
		System.out.println("Title:"+Title+" Reply:"+Reply);
		if (Account.isEmpty()|| ADid.isEmpty()|| Vid.isEmpty()|| Title.isEmpty()|| Reply.isEmpty())
		{
			System.out.println("Account:"+Account+"ADid:"+ADid+"Vid:"+Vid+"Title:"+Title+"Reply:"+Reply);
			return null;
		}
		//System.out.println(1);
		//System.out.println("Account:"+Account+" "+"Title:"+Title);
		Account = java.net.URLDecoder.decode(Account, "UTF-8");
		Title = java.net.URLDecoder.decode(Title, "UTF-8");
		Reply = java.net.URLDecoder.decode(Reply, "UTF-8");
		//System.out.println(2);
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SuperAdminReplyAdviceService superAdminReplyAdviceService = (SuperAdminReplyAdviceService) factory
				.getBean("SuperAdminReplyAdviceService");
		SuperAdminReplyAdviceModel superAdminReplyAdviceModel = new SuperAdminReplyAdviceModel();
		superAdminReplyAdviceModel.setADid(ADid);
		superAdminReplyAdviceModel.setAccount(Account);
		superAdminReplyAdviceModel.setVid(Vid);
		superAdminReplyAdviceModel.setTitle(Title);
		superAdminReplyAdviceModel.setReply(Reply);

		if (superAdminReplyAdviceService
				.replyAdvice(superAdminReplyAdviceModel)) {
			JSONArray jsonArray = superAdminReplyAdviceService
					.getSoftwareAdvices(superAdminReplyAdviceModel);

			return jsonArray;
		}
		return null;
	}

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SuperAdminReplyAdviceService superAdminReplyAdviceService = (SuperAdminReplyAdviceService) factory
				.getBean("SuperAdminReplyAdviceService");
		SuperAdminReplyAdviceModel superAdminReplyAdviceModel = new SuperAdminReplyAdviceModel();
		superAdminReplyAdviceModel.setADid("3");
		superAdminReplyAdviceModel.setAccount("Hello");
		superAdminReplyAdviceModel.setVid("1");
		superAdminReplyAdviceModel.setTitle("超级管理员信件");
		superAdminReplyAdviceModel.setReply("你好，你的建议非常合理");

		System.out.println("replyStatus:"
				+ superAdminReplyAdviceService
						.replyAdvice(superAdminReplyAdviceModel));
		// System.out.println("adviceStatus:"+superAdminReplyAdviceService.getAdviceStatus(superAdminReplyAdviceModel).getStatus());
		// superAdminReplyAdviceModel.setAid("1");
		// System.out.println(superAdminReplyAdviceService.changeAdviceStatus(superAdminReplyAdviceModel));
		// superAdminReplyAdviceModel.setVid("1");
		// superAdminReplyAdviceModel=superAdminReplyAdviceService.getVisitorEmail(superAdminReplyAdviceModel);
		// System.out.println("visitorEmail:"+superAdminReplyAdviceModel.getEmail());
		// superAdminReplyAdviceModel.setAccount("Hello");
		// JSONArray jsonArray = superAdminReplyAdviceService
		// .getSoftwareAdvices(superAdminReplyAdviceModel);
		// System.out.println(jsonArray);
		// int softwareAdviceNumber = superAdminReplyAdviceService
		// .getSoftwareAdviceNumber(superAdminReplyAdviceModel);
		// System.out.println("softwareAdviceNumber:" + softwareAdviceNumber);

	}
}
