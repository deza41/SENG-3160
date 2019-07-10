<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 25/05/2017
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
    <sx:head />
    <title>Create Event</title>

    <script type="text/javascript">
        function multipleTimes(tableID){
            var startDate = document.getElementById("startDate");
            var endDate = document.getElementById("endDate");
            var totalDays = endDate - startDate;
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);

            var oneDay = 24*60*60*1000; // hoursminutessecondsmilliseconds
            var dateArray = $("#startDate > input:first").val().split("-");
            var year = dateArray[0];
            var month = dateArray[1];
            var day = dateArray[2].split("T")[0];
            var firstDate = new Date(year,month,day);
            var dateArray = $("#endDate > input:first").val().split("-");
            var year = dateArray[0];
            var month = dateArray[1];
            var day = dateArray[2].split("T")[0];
            var secondDate = new Date(year,month,day);
            var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay)));

            $('.timeItem').remove();

            var rowNum = 1;
            var date = new Date();

            for(count = 0; count < diffDays + 1; count++){

                date.setDate(firstDate.getDate() + count);
                insertRow(tableID, rowNum, date);
                rowNum++;
            }
        }

        function insertRow(tableID, rowNum, date){
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);

            row.className = "timeItem";

            var cell1 = row.insertCell(0);   //to insert first column
            var dayCol = document.createElement("P");
            var text = document.createTextNode(rowNum.toString());
            dayCol.appendChild(text);
            cell1.appendChild(dayCol);

            var cell2 = row.insertCell(1); //to insert second column
            addPicker(cell2, date, 1);

            var cell3 = row.insertCell(2); // to insert 3rd column
            addPicker(cell3, date, 2);
        }

        function singleTime(tableID){
            var startDate = document.getElementById("startDate");
            var dateArray = $("#startDate > input:first").val().split("-");
            var year = dateArray[0];
            var month = dateArray[1];
            var day = dateArray[2].split("T")[0];
            var firstDate = new Date(year,month,day);

            $('.timeItem').remove();
            insertRow(tableID, 1, firstDate)
        }

        function addPicker(elementToAppend, date, number){
            var year = date.getFullYear();
            var month = date.getMonth();
            var day = date.getDate();
            var idString = year + "-" + month + "-" + day + "-" + number;
            var element = '<div class="time-picker-element" id="'+idString+'"/>';


            $(element).appendTo(elementToAppend).timepicker({
                controlType: 'select',
                timeFormat: 'hh:mm tt'
            });
        }


        function timeString(){
            //make the objects
            var retObjs = [];

            $.each($('.time-picker-element'), function() {
                var timeObject = new Object();
                timeObject.timeId = $(this).attr('id');
                timeObject.timeAmount = $(this).val();
                if(timeAmount == ""){
                    timeAmount = "07:00 am";
                }
                retObjs.push(timeObject);
            })

            // now we build the string

            var dataString = '{"eventTimes":[';

            $.each(retObjs, function() {
                dataString += JSON.stringify($(this)[0], ['timeId', 'timeAmount']) + ',';
            });


            // remove last , character
            dataString = dataString.substring(0, dataString.length - 1);

            dataString += ']}';

            $.ajax({
                type: "POST",
                url: "createEventSaveTimes.action",
                data : dataString,
                dataType:"JSON",
                contentType: "application/json; charset=utf-8"
            });
        }


    </script>

    <style>
        table {
            margin: auto;
        }
        td {
            padding: 0.25rem;
        }
    </style>

</head>
<body>
<div class="container text-center">

    <div class="section-header">
        <h2>Create Event</h2>
    </div>
    <s:actionerror/>

    <s:form name="eventForm" onsubmit="timeString()" action="createEvent" method="post" enctype="multipart/form-data" theme="%{currentTheme}">
        <s:hidden name="rowindex"/>
        <s:textfield name="title" label="Title"/>
        <s:select class="custom-select" label="Select a category" headerKey="-1" headerValue="Select Category" list="#{ 'food': 'food', 'experience':'experience', 'accommodation':'accommodation'}" name="category" value="6" />
        <s:textfield name="eventDescription" label="Description"/>
        <s:file name="eventImageFile" label="Event Image"/>
        <s:textfield name="oldPrice" label="Listing price"/>
        <s:textfield name="price" label="Discounted price"/>
        <sx:datetimepicker id="startDate" name="startDate" label="Event Start Date (dd-MMM-yyyy)"
        displayFormat="dd-MMM-yyyy" value="%{'today'}" />
        <sx:datetimepicker id="endDate" name="endDate" label="Event End Date (dd-MMM-yyyy)"
        displayFormat="dd-MMM-yyyy" value="%{'today'}" />


        <table class="mt-4" id="timeTable">
            <tr>
                <td><input type="button" value="Same Time Every Day" onclick="singleTime('timeTable')"/></td>
                <td><input type="button" value="Individual Times" onclick="multipleTimes('timeTable')"/></td>
            </tr>
            <tr>
                <th>Day</th>
                <th>Start Time</th>
                <th>End Time</th>
            </tr>

        </table>

        <table>
            <tr>
                <td align="center">
                    <s:submit value="Submit" align="center"/>
                </td>
            </tr>
        </table>
    </s:form>
</div>
</body>
</html>