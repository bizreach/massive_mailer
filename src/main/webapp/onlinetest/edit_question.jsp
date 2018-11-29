<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    String receivedMessage = (String) request.getAttribute("receivedMessage");
    pageContext.setAttribute("receivedMessage", receivedMessage);

	String description = (String) request.getAttribute("description");
%>



<t:with_side_menu_and_status title="Edit Question">
    <jsp:body>
			<form name="editQuestion" id="editQuestion" method="post"
				action="edit_question">

					<div class="row">
						<div class="col-lg-12">

							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Question Information</h3>
								</div>
								<div class="panel-body">
								<c:if test="${not empty errorMessage}">
                                    <div id="message" class="alert alert-danger" role="alert">
                                        ${errorMessage}
                                    </div>
                                </c:if>
									<div class="row">
										<div class="col-lg-1">Description:</div>
										<div class="col-lg-11">
											<input type="text" class="form-control" name="description"
												id="description" maxlength="200" value="2 +3 = ?">
										</div>
									</div>
									<br> <br>

									<div class="row">
										<div class="col-lg-1">Options:</div>
										<div class="col-lg-11">

										<c:forEach var="i" begin="1" end="6" step="1">
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                <input type="radio" id="option${i}" name="check" value="${i}" aria-label="Radio button for following text input">
                                                </span>
                                                <input type="text" id="option${i}_value" class="form-control" name="option${i}" aria-label="Text input with radio button" width="100%" maxlength="100" value="${i}">
                                              </div>
                                          <br>
                                        </c:forEach>
                                        </div>
									</div>
									<br> <br>

									<div class="row">
										<div class="col-lg-1">Advice:</div>
										<div class="col-lg-11">
											<input type="text" class="form-control" name="advice"
												id="advice" maxlength="500" value="2 + 3 = 5です">
										</div>
									</div>
									<br> <br>
									<div class="row">
                                        <div class="col-lg-6">
                                            <button type="submit" class="btn btn-default" name="cancel-button"
                                                    id="cancel-button" value="cancel" >Cancel</button>
                                        </div>
										<div class="col-lg-6">
											<button type="submit" class="btn btn-default" name="save-button"
												id="save-button" value="save" >Save</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
			</form>
    </jsp:body>
</t:with_side_menu_and_status>
