<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:with_side_menu title="Question List">
    <jsp:body>
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Question List</h1>
					</div>
				</div>
				<!-- /.row -->

				<input type="hidden" id="msg_sent_cnt" value="${param.repcnt}" />
					<div class="col-lg-12" id="questionTables">
						<div class="panel panel-default">
                            <table class="table table-responsive table-bordered">
                                <thead>
                                <tr>
                                    <th>QuestionID</th>
                                    <th>Description</th>
                                    <th>Edit</th>
                                </tr>
                                </thead>
                                <tbody id="questionTable">
                                <tr>
									<td>1</td>
									<td>Scrumの用語はどれか？</td>
									<td><button>Edit</button></td>
                                </tr>
                                </tbody>
                            </table>
						</div>
					</div>

			<div class="modal fade" id="editContactModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Edit Contact</h4>
						</div>


					</div>
				</div>
			</div>
		</div>
    </jsp:body>
</t:with_side_menu>




