package com.controller.pages;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.el.stream.Stream;
import org.apache.tomcat.util.http.parser.MediaType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dao.RegistrationDao;
import com.pojo.pages.Registration1;
import com.pojo.pages.Registration2;
import com.pojo.pages.Registration3;
import com.pojo.pages.RegistrationPojo;

import jakarta.annotation.Resource;
import util.RandonValueGenerator;

//@RestController
@Controller
public class MainControllerAadhar {

	
	// getting details by postman
	
	  @RequestMapping(value = "/sample", method = RequestMethod.GET) public String
	  getData() {
	  
	  return "hello"; }
	
	/*
	 * @RequestMapping(value = "/aadharform", method = RequestMethod.POST)
	 * 
	 * public ModelAndView register(HttpServletRequest request, HttpServletResponse
	 * response) { RegistrationDao regObj = new RegistrationDao(); Session session1
	 * = regObj.registDao(); org.hibernate.Transaction txt =
	 * session1.beginTransaction();
	 * 
	 * ModelAndView mv = new ModelAndView(); mv.setViewName("GetAadharDetails.jsp");
	 * System.out.println("hii"); String fname = request.getParameter("first");
	 * String lname = request.getParameter("last"); long phoneNo =
	 * Long.parseLong(request.getParameter("phone")); String dof =
	 * request.getParameter("dob"); String emailID = request.getParameter("email");
	 * String gender = request.getParameter("s"); String address =
	 * request.getParameter("address"); String state =
	 * request.getParameter("state"); int pincode =
	 * Integer.parseInt(request.getParameter("pincode")); Registration1 tab1 = new
	 * Registration1();
	 * 
	 * RandonValueGenerator aadharNumObject = new RandonValueGenerator(); long
	 * aadharNo = aadharNumObject.generateRandom(12);
	 * 
	 * tab1.setAadharNo(aadharNo); Registration2 tab2 = new Registration2();
	 * tab2.setRegistration1(tab1); tab2.setDob(dof); tab2.setEmail(emailID);
	 * tab2.setFname(fname); tab2.setLname(lname); tab2.setGender(gender);
	 * tab2.setPhoneNo(phoneNo);
	 * 
	 * Registration3 tab3 = new Registration3(); tab3.setRegistration2(tab2);
	 * tab3.setAddress(address); tab3.setPincode(pincode); tab3.setState(state);
	 * 
	 * 
	 * session1.save(tab1); session1.save(tab2); session1.save(tab3);
	 * 
	 * txt.commit(); return mv;
	 * 
	 * }
	 */

	// getting details by postman

	/*
	 * @RequestMapping(value = "/GetAadharDetail", method =
	 * RequestMethod.POST,consumes =
	 * org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public List getDetails(@RequestBody RegistrationPojo
	 * registrationPojo) {
	 * 
	 * System.out.println(phoneNo); BigInteger
	 * phoneNo=registrationPojo.getPhoneNo(); List<RegistrationPojo> listObject =
	 * null; RegistrationDao regObj = new RegistrationDao(); Session session1
	 * =regObj.registDao(); org.hibernate.Transaction txt
	 * =session1.beginTransaction();
	 * 
	 * Query query = session1.
	 * createSQLQuery("SELECT s.fname, s.lname, s.gender, s.email, s.dob,s.phoneNo,f.aadharNo,t.address,t.pincode,t.state "
	 * + " FROM FirstRegist f JOIN SecondRegist s ON f.cid = s.registration1_cid " +
	 * "JOIN ThirdRegist t ON s.phoneNo = t.registration2_phoneNo WHERE phoneNo='"
	 * +phoneNo+"'");
	 * 
	 * query.setResultTransformer(Transformers.aliasToBean(RegistrationPojo.class));
	 * listObject = query.list(); System.out.println("details are:" +
	 * listObject.toString());
	 * 
	 * 
	 * return listObject; }
	 */

	
	// getting details displayed on jsp by phone no

	
	  @RequestMapping(value = "/GetAadharDetail", method = RequestMethod.POST)
	  public ModelAndView getDetails(HttpServletRequest request
	  ,HttpServletResponse response) { long phoneNo =
	  Long.parseLong(request.getParameter("phoneNo")); System.out.println(phoneNo);
	  List<RegistrationPojo> listObject = null; RegistrationDao regObj = new
	  RegistrationDao(); Session session1 = regObj.registDao();
	  org.hibernate.Transaction txt = session1.beginTransaction();
	  
	  Query query = session1.
	  createSQLQuery("SELECT s.fname, s.lname, s.gender, s.email, s.dob,s.phoneNo,f.aadharNo,t.address,t.pincode,t.state "
	  + " FROM FirstRegist f JOIN SecondRegist s ON f.cid = s.registration1_cid " +
	  "JOIN ThirdRegist t ON s.phoneNo = t.registration2_phoneNo WHERE phoneNo='"+phoneNo+"'");
	  
	  query.setResultTransformer(Transformers.aliasToBean(RegistrationPojo.class));
	  listObject = query.list(); System.out.println("details are:" +
	  listObject.toString());
	  
	  ModelAndView mv = new ModelAndView();
	  
	  
	  mv.addObject("res",listObject); mv.setViewName("viewDetails.jsp");
	  
	  return mv; }
	 
	  // getting details displayed on jsp by aadhar no
	  
	  @RequestMapping(value = "/GetAadharDetailsByAadhar", method = RequestMethod.POST)
	  public ModelAndView getDetailsByAadhar(HttpServletRequest request ,HttpServletResponse response) { 
		  long aadharNo = Long.parseLong(request.getParameter("aadhar"));
		  System.out.println(aadharNo);
	  List<RegistrationPojo> listObject = null; 
	  RegistrationDao regObj = new RegistrationDao(); 
	  Session session1 = regObj.registDao();
	  org.hibernate.Transaction txt = session1.beginTransaction();
	  
	  Query query = session1.createSQLQuery("SELECT s.fname, s.lname, s.gender, s.email, s.dob,s.phoneNo,f.aadharNo,t.address,t.pincode,t.state "
	  + " FROM FirstRegist f JOIN SecondRegist s ON f.cid = s.registration1_cid " +
	  "JOIN ThirdRegist t ON s.phoneNo = t.registration2_phoneNo WHERE aadharNo='" +aadharNo+"'");
	  
	  query.setResultTransformer(Transformers.aliasToBean(RegistrationPojo.class));
	  listObject = query.list(); System.out.println("details are:" +listObject.toString());
	  
	  ModelAndView mv = new ModelAndView();
	  
	  
	  mv.addObject("res",listObject); mv.setViewName("viewDetails.jsp");
	  
	  return mv; }
	/*
	 * @RequestMapping(value = "", method = RequestMethod.POST) public ModelAndView
	 * updateByAadhar(HttpServletRequest request, HttpServletResponse response) {
	 * int AadharNo;
	 * 
	 * return null;
	 * 
	 * }
	 */

	@RequestMapping(value = "updateByPhoneNo", method = RequestMethod.POST)
	public ModelAndView updateByPhoneNo(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		RegistrationDao registrationDao = new RegistrationDao();
		Session session1 = registrationDao.registDao();
		//RegistrationPojo registrationPojo=new RegistrationPojo();
		  org.hibernate.Transaction txt = session1.beginTransaction();
		  List<RegistrationPojo>listobj=new ArrayList<RegistrationPojo>();
		String fname=request.getParameter("first");
		String lname=request.getParameter("last");
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String pincode=request.getParameter("pincode");
		String state=request.getParameter("state");
		String dob=request.getParameter("dob");
		Query query = session1.createSQLQuery(" UPDATE FirstRegist f INNER JOIN SecondRegist s ON f.cid=s.registration1_cid JOIN ThirdRegist t ON s.phoneNo=t.registration2_phoneNo SET s.fname='"+fname+"'"
				+ ",s.lname='"+lname+"',s.dob='"+dob+"',s.email='"+email+"',t.pincode='"+pincode+"',t.address='"+address+"',t.state='"+state+"',s.gender='"+gender+"',s.phoneNo='"+phone+"'"
				+ "	WHERE s.phoneNo='"+phone+"'");
	
		int n = query.executeUpdate();
		
		System.out.println(n);
		 Query query1 = session1.
				  createSQLQuery("SELECT s.fname, s.lname, s.gender, s.email, s.dob,s.phoneNo,f.aadharNo,t.address,t.pincode,t.state "
				  + " FROM FirstRegist f JOIN SecondRegist s ON f.cid = s.registration1_cid " +
				  "JOIN ThirdRegist t ON s.phoneNo = t.registration2_phoneNo WHERE phoneNo='"+phone+"'");
		 listobj=query1.list();
		 txt.commit();
		System.out.println("updated data are:"+listobj.toString());
		mv.setViewName("updatedData.jsp");
		return mv;
}
	
	@RequestMapping(value = "updateByAadhar", method = RequestMethod.POST)
	public ModelAndView updateByAadhar(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		RegistrationDao registrationDao = new RegistrationDao();
		Session session1 = registrationDao.registDao();
		 org.hibernate.Transaction txt = session1.beginTransaction();
		String aadhar=request.getParameter("aadhar");
		String fname=request.getParameter("first");
		String lname=request.getParameter("last");
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String pincode=request.getParameter("pincode");
		String state=request.getParameter("state");
		String dob=request.getParameter("dob");
		Query query = session1.createSQLQuery(" UPDATE FirstRegist f INNER JOIN SecondRegist s ON f.cid=s.registration1_cid JOIN ThirdRegist t ON s.phoneNo=t.registration2_phoneNo SET s.fname='"+fname+"'"
				+ ",s.lname='"+lname+"',s.dob='"+dob+"',s.email='"+email+"',t.pincode='"+pincode+"',t.address='"+address+"',t.state='"+state+"',s.gender='"+gender+"',s.phoneNo='"+phone+"'"
				+ "	WHERE s.aadharNo='"+aadhar+"'");
	int n = query.executeUpdate();
	System.out.println(n);
		 txt.commit();
		mv.setViewName("updatedData.jsp");
		return mv;
}

}
