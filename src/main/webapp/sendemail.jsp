<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%@ page import="com.odde.massivemailer.model.ContactPerson"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<title>ODD-E</title>

<!-- Bootstrap Core CSS -->
<link href="/resources/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/resources/lib/bootstrap/css/sb-admin.css" rel="stylesheet">

<link href="/resources/lib/bootstrap/css/plugins/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="/resources/lib/bootstrap/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<style>
#selectContactTable li {
	height: 25px;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
	<form name="sendmail" id="sendmail" method="post">
            <jsp:include page="ui_common.jsp" />

			<div id="page-wrapper">

				<div class="container-fluid">

					<!-- Page Heading -->
					<div class="row">
						<div class="col-lg-12">
							<h1 class="page-header">Edit Template</h1>
						</div>
					</div>
					<!-- /.row -->
					<%
						if (request.getParameter("status") != null) {
							out.println(
									"<div class='row'><div class='col-lg-12'><div class='alert alert-info alert-dismissable'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><i class='fa fa-info-circle'></i>&nbsp;<b><span id='email_result'>"
											+ request.getParameter("status") + " : " + request.getParameter("msg")
											+ "</span></b></div> </div></div>");
						}
					%>
					<input type="hidden" id="msg_sent_cnt" value="${param.repcnt}" />
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Edit Template</h3>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-lg-1">Template:</div>
										<div class="col-lg-11">
											<select name="templateList" id="templateList"></select>
											
										</div>
									</div>

									<br />
									<div class="row">
										<div class="col-lg-1">To:</div>
										<div class="col-lg-11">
											<input type="text" class="form-control" name="recipient"
												id="recipient"> 
											<span id="select_contact" data-toggle="modal" data-target="#selectContactModal"
											class="glyphicon glyphicon-plus add-contact-button"
											aria-hidden="true"></span>
											
										</div>
									</div>
									<br />
									<div class="row">
										<div class="col-lg-1">Subject:</div>
										<div class="col-lg-11">
											<input type="text" class="form-control" name="subject"
												id="subject">
										</div>
									</div>
									<br />
									<div class="row">
										<div class="col-lg-12">
											<textarea class="form-control" rows="20" name="content"
												id="content"></textarea>
										</div>
									</div>
									<br />
									<div class="row">
										<div class="col-lg-1">
											<button type="button" class="btn btn-default"
												id="send_button" value="send" disabled="disabled">Send</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-default"
												id="update_button" value="update" >Update</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="select_contact.jsp" %>
	</form>
</body>

<!-- jQuery -->
<script type="text/javascript" src="resources/lib/bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="resources/lib/jquery-template/jquery.loadTemplate-1.4.4.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="resources/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/sendemail.js"></script>
<script type="text/javascript" src="resources/js/showContact.js"></script>
<script type="text/javascript" src="resources/js/selectContact.js"></script>
<script type="text/javascript" src="resources/js/showTemplate.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#select_contact').click(function () {
			var contactList = retrieveContactListFromServer();
			selectedContact = '';
			renderContactSelectionList(contactList, $('#selectContactTable'));
		});

		$('#add_contact_button').click(function () {
			whenAddButtonIsClicked();
		});





	});
</script>
</html>