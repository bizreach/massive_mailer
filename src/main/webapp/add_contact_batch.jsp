<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact List</title>
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
</head>
<body>
        <jsp:include page="ui_common.jsp" />
		<div id="page-wrapper">
			<form name="batchContacts" id="batchContacts" method="post" action="batchContacts">
				<div class="container-fluid">

					<!-- Page Heading -->
					<div class="row">
						<div class="col-lg-12">
							<h1 class="page-header">Add Contact Batch</h1>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-12">

							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">File Information</h3>
								</div>
								<div class="panel-body">
                                <div class="row" style="padding:0 20px">
                                    <input type="file" name="myfile" id="batchFile" accept=".csv"/>
                                    <input type="text" name="data" id="data" style="display:none"/>
								</div>
								</div>
						</div>
					</div>
					</form>
				</div>

			</form>
		</div>
</body>
<!-- jQuery -->
<script type="text/javascript"
	src="resources/lib/bootstrap/js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="resources/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/addContactBatch.js"></script>
</script>
</html>