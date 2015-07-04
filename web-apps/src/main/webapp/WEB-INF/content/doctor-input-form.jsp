<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Doctor Input Form</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <!-- bootstrap select -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.3/css/bootstrap-select.min.css">

    <!-- bootstrap datetimepicker -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.14.30/css/bootstrap-datetimepicker.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <div class="container">
        <div id="create-request-success-alert" ></div>
        <h1>Patient Exercise Prescription</h1>
        <form id="create-request-form" class="form-horizontal">
            <input type="hidden" name="requesterId" value="1"/>
            <div class="form-group">
                <label for="patient" class="col-md-2 control-label">Patient</label>
                <div class="col-md-10">
                    <select id="patient" name="clientId" class="selectpicker">
                        <option value="">Please select...</option>
                        <option value="1">Peter</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="duration-start" class="col-md-2 control-label">Duration Start at</label>
                <div class="col-md-3">
                    <div class="input-group date datetimepicker" id="duration-start">
                        <input type="text" class="form-control" name="start"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="duration-end" class="col-md-2 control-label">Duration End at</label>
                <div class="col-md-3">
                    <div class="input-group date datetimepicker" id="duration-end">
                        <input type="text" class="form-control" name="end"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="frequency" class="col-md-2 control-label">Frequency</label>
                <div class="col-md-2">
                    <input type="number" id="frequency" name="frequency" class="numeric form-control" min="1"/>
                </div>
            </div>
            <div class="form-group">
                <label for="sport-type" class="col-md-2 control-label">Sport Type</label>
                <div class="col-md-10">
                    <select id="sport-type" name="sportType" class="selectpicker">
                        <option value="">Please select...</option>
                        <option value="RUNNING">Running</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="intensity" class="col-md-2 control-label">Intensity</label>
                <div class="col-md-10">
                    <textarea id="intensity" name="intensity" rows="3" class="form-control"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <button type="button" id="send-request-btn" class="btn btn-primary">Send Request</button>
                </div>
            </div>
        </form>
    </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!-- bootstrap select -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.3/js/bootstrap-select.min.js"></script>
    <!-- bootstrap datetimepicker -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.14.30/js/bootstrap-datetimepicker.min.js"></script>
    <!-- jquery numeric -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.numeric.min.js"></script>
    <!-- jquery-serialize-object -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-serialize-object/2.0.0/jquery.serialize-object.compiled.js"></script>
    <spring:url var="createRequestRestfulUrl" value="/api/v1/exercise-requests/"/>
    <script>
        $(function() {
            $('.datetimepicker').datetimepicker({
                viewMode: 'days',
                format: 'DD-MMM-YYYY'
            });
            $('.numeric').numeric();

            $('#send-request-btn').click(function() {
                $.ajax({
                    url: '${createRequestRestfulUrl}',
                    data: JSON.stringify($('#create-request-form').serializeObject()),
                    contentType: 'application/json; charset=utf-8',
                    dataType:'json',
                    type:'POST',
                    success: function(data) {
                        window.console && console.log(data);
                        $('#create-request-success-alert').html('');
                        $('#create-request-success-alert').html('<div class="alert alert-success alert-dismissible fade in" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>Request is created</div>');
                    }
                });
            });
        });
    </script>
</body>
</html>
