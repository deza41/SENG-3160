<%--
  Created by IntelliJ IDEA.
  User: blake
  Date: 10/05/2017
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>

<%-- Planning page for a user once they have selected their deals/vouchers.

Allows users to visually plan their trip, by choosing time slots to place deals.
The itinerary view will automatically display events that they are attending and
recommend further addons while they fill it out.
Planned to add drag and drop functionality for placing deals into time slots.


 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <%@ taglib prefix="s" uri="/struts-tags" %>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Itinerary.css"/>

    <script type="text/javascript">
        var changesMade = false; // this tracks for whether to ask user they are sure before leaving the page
        var ignoreFirstTextBoxInitChange = 2;   // a hack because when textbox are initialised (not on page load) they trigger
                                                // on change events sometimes
        var containerForScroll; // this just tracks which element to scroll the user to on page load
        var number_of_days_to_show = 3;

        // constants for drawing the itinerary correctly
        const NUMBER_OF_INTERVALS = 4; // 15min intervals
        const INTERVAL_AMOUNT = 15;
        const INTERVAL_HEIGHT = 50;


        $(document).ready(function() {
            // init will grab all the data fields from itinerary-items and itinerary-stores and initialise draggables
            // and droppables.
            // postinit is then run for stuff that requires all drags/drops to be initialised first.
            // Events are init last just so things are seperated out a bit but works mostly like the itinerary items
            // without any manipulation for users available

            $.when( init() ).done(function() {
                postinit();
            });
            $(initEvents());
            changesMade = false;
        });

        // small helper function to show a popup at a target element
        function DisplayPopup(el, message){
            $('<div class="card alert-popup"><div class="card-block">' + message + '</div></div>').insertAfter(el)
                .delay(30000)
                .fadeOut(function() {
                    $(this).remove();
                });
        };

        $(window).bind('beforeunload', function(){
            if(changesMade)
                return 'Unsaved changes';
        });

        function initEvents(){
            function attachEventTo(eventElement, targetElement) {
                // if the day exists attach it on the itinerary, otherwise, hide the event as it has no day to appear on
                if($(targetElement).length > 0) {
                    // init the droppable
                    eventElement.data("stored-slot-id", targetElement);

                    // physically append the html element to the droppable element so it is styled correctly
                    $(targetElement).append(eventElement);

                    // make it look right
                    eventElement.addClass("col-1");

                    eventElement.css({
                        "top": "0"
                    });
                }
                else{
                    eventElement.hide();
                }
            }

            function expandEventItem(eventItem){
                eventItem.find('.event-item-expand').toggle( 'fold', {}, 500, null );
            }

            $('.event-item-expand-button').click(function() {
                expandEventItem($(this).parent().parent());
                $(this).find('i').toggleClass('fa-caret-left');
                $(this).find('i').toggleClass('fa-caret-right');
            });

            $.each($('.event-item'), function() {
                var id = $(this).data("stored-slot-id");
                if(id) {
                    attachEventTo($(this), '#day-' + id);
                }

                $(this).height($(this).data('stored-interval-length') * INTERVAL_HEIGHT);

            });
        }

        function postinit() {

            $(function() {
                containerForScroll.scrollTop(
                    scrollTo.offset().top - containerForScroll.offset().top + containerForScroll.scrollTop()
                );
            });

            function attachDraggableToInit(draggableElement, targetElement) {

                // init the droppable
                $(targetElement).data("stored-item-id", draggableElement);
                draggableElement.data("stored-slot-id", targetElement);
                $(targetElement).droppable("disable");

                // physically append the html element to the droppable element so it is styled correctly
                $(targetElement).append(draggableElement);

                // make it look right
                draggableElement.addClass("itinerary-item-placed offset-3 col-9");
                draggableElement.resizable("enable");

                draggableElement.css({
                    "top": "0",
                    "left": "0"});

            }

            $.each($('.itinerary-item'), function() {
                var id = $(this).data("stored-slot-id");
                if(id) {
                    attachDraggableToInit($(this), '#day-' + id);
                }

                $(this).height($(this).data('stored-interval-length') * INTERVAL_HEIGHT);
            });


        }


        function isBreakpoint( alias ) {
            return $('.device-' + alias).is(':visible');
        }

        function constructDay(attachmentElement, elementId, day, startHour, endHour){
            var dayElement = '<div id="' + elementId + '" class="itinerary-day-column col"> Day: ' + day;

            dayElement += '<div class="accomodation"></div>';

            for(currentHour = startHour; currentHour < endHour; currentHour++) {

                dayElement += generateHour(elementId, currentHour);

            }

            dayElement += '</div>';

            attachmentElement.append(dayElement);

        }

        function generateHour(parentId, hourNumber){
            var eleId = parentId + '-' + hourNumber;
            var elements = '<div id="' + eleId + '" class="row no-gutters itinerary-hourly-wrapper">'

            for(interval = 0; interval < NUMBER_OF_INTERVALS; interval++) {
                var classNames = 'itinerary-droppable-slot'
                var intervalId = eleId + '-' + interval;

                if(interval == 0)
                    classNames += ' itinerary-droppable-slot-first'

                elements += '<div id="' + intervalId + '" class="' + classNames + '"><span class="itinerary-interval-text">' + hourNumber + calculateInterval(interval) + '</span></div>'
            }

            return elements + '</div>'
        }

        function calculateInterval(n){

            if(n == '0')
                return ':00'

            return ':' + INTERVAL_AMOUNT * n
        }

        function showDay(id){
            $("#" + id).show();
        }

        function hideDay(id){
            $("#" + id).hide();
        }

        function init() {

            //for(day = 1; day <= 10; day++) {
            //    constructDay($('#itineraryArea'), 'day-' + day + '-09-2017', day + '/09/2017', 0, 24);
            //}

            var listOfDays = [];
            var daysShown = 0;

            $('.itineraryDaysHidden').each(function() {
                var dayId = $(this).attr("id");

                constructDay($('#itineraryArea'), 'day-' + dayId, dayId, 0, 24);
                hideDay('day-' + dayId);
                listOfDays.push('day-' + dayId);

                if(daysShown < number_of_days_to_show){
                    daysShown++;
                    showDay('day-' + dayId);

                    containerForScroll = $('#itineraryArea'),
                        scrollTo = $('#' + 'day-' + dayId + '-7');

                }
            });

            $('input').change(function(){
                if(ignoreFirstTextBoxInitChange > 0)
                    ignoreFirstTextBoxInitChange--;
                else {
                    changesMade = true;

                    console.log("Changes made 3");
                }

            });

            $('#switch-itinerary-view').click(function() {
                if(number_of_days_to_show == 3) {
                    number_of_days_to_show = 1;

                    $('.event-item-expand').css({
                        "margin-left": "63px"
                    });
                }
                else {
                    number_of_days_to_show = 3;

                    $('.event-item-expand').css({
                        "margin-left": "16px"
                    });
                }

                ShowItineraryDays($("#daySlider").slider("option", "max", CalculateDaySliderMax()));
                ShowItineraryDays($("#daySlider").slider("value"), number_of_days_to_show);

            });

            $('#save-itinerary').click(function() {
                changesMade = false;
                var retObjs = [];

                $.each($('.itinerary-item'), function() {

                    var intervals = $(this).data('stored-interval-length');

                    if(intervals == undefined)
                        intervals = 1;

                    var disgustingSplit = $(this).data('stored-slot-id').split("-");
                    var dayId = disgustingSplit[1] + "-" + disgustingSplit[2] + "-" + disgustingSplit[3];

                    if(disgustingSplit[1] == undefined || disgustingSplit[2] == undefined || disgustingSplit[3] == undefined) {
                        dayId = "UNASSIGNED";
                    }
                    var hourNumber = disgustingSplit[4];
                    var intervalNumber = disgustingSplit[5];
                    var intervalAmount = intervals;
                    var localBookingID = $(this).data('local-booking-id');;
                    var numberOfChildren = $(this).find('.itinerary-item-expand > .numberOfChildren').val();
                    var numberOfAdults = $(this).find('.itinerary-item-expand > .numberOfAdults').val();

                    var itineraryObject = new Object();
                    itineraryObject.itineraryItemId = $(this).attr('id');
                    itineraryObject.dayId = dayId;
                    itineraryObject.hourNumber = hourNumber;
                    itineraryObject.intervalNumber = intervalNumber;
                    itineraryObject.intervalAmount = intervalAmount;
                    itineraryObject.localBookingID = localBookingID;
                    itineraryObject.numberOfChildren = numberOfChildren;
                    itineraryObject.numberOfAdults = numberOfAdults;
                    retObjs.push(itineraryObject);
                });


                var dataString = '{"itineraryItems":[';

                $.each(retObjs, function() {
                    dataString += JSON.stringify($(this)[0], ['itineraryItemId', 'dayId', 'hourNumber', 'intervalNumber', 'intervalAmount', 'localBookingID', 'numberOfChildren', 'numberOfAdults']) + ',';
                });

                // small hack, nevermind me
                // remove last , character
                dataString = dataString.substring(0, dataString.length - 1);

                dataString += ']}';

                console.log(dataString);


                $.ajax({
                    type : "POST",
                    url : "itinerarySave.action",
                    dataType : "json",
                    data: dataString,
                    contentType: "application/json; charset=utf-8",
                    async: true,
                    success : function(result) {
                        changeMade = false;
                        DisplayPopup($('#save-itinerary'), "Successfully saved.");


                        $.each(result.itineraryItems, function() {
                            // 30-08-2017-8-0
                            var itinId = '#day-' + $(this)[0].dayId + '-' + $(this)[0].hourNumber + '-' + $(this)[0].intervalNumber;

                            $(itinId).find('.itinerary-item').data("local-booking-id", $(this)[0].localBookingID)
                        });

                    },
                    complete: function (msg,a,b) {
                    },
                    error : function(msg,a,b){
                        console.log(msg);
                        console.log('error:'+msg);
                    }
                });





            });

            $('.itinerary-item').draggable({
                revert: true,
                revertDuration: 150,
                snap: ".itinerary-droppable-slot",
                snapMode: "inner",
                snapTolerance: -20,
                start: function (event, ui) {
                    if($(this).is(".itinerary-item-placed")){
                        $(this).draggable("option", "revert", true);
                        var dropElement = $($(this).data('stored-slot-id'));
                        dropElement.droppable("enable");
                        dropElement.data("stored-item-id", null);
                    }
                },
                stop: function (event, ui) {
                    $(this).height($(this).data('stored-interval-length') * INTERVAL_HEIGHT);
                }
            });

            $('.itinerary-item > .card-block').click(function(){
                //$(this).parent('.itinerary-item').find('.itinerary-item-expand').toggle( 'blind', {}, 500, null );
                expandItineraryItem($(this).parent());
            });

            function expandItineraryItem(itineraryItem){
                itineraryItem.find('.itinerary-item-expand').toggle( 'blind', {}, 500, null );
                itineraryItem.toggleClass( "itinerary-item-expanded", 50 );

            }

            $('.itinerary-item').resizable({
                grid: INTERVAL_HEIGHT,
                handles: 's, se',
                resize: function(event, ui) {

                    SwitchSlots("enable", $($(this).data('stored-slot-id')), $(this).data('stored-interval-length'));


                    changesMade = true;
                    console.log("Changes made 1");
                    $(this).css('width','');
                    $(this).data("stored-interval-length", getItemIntervalsAmount($(this)));
                    var resizingSlotId = $(this).data("stored-slot-id");
                    var resizingElementId = $(this).id;
                    var resizingElement = $(this);
                    var limits = [];

                    SwitchSlots("disable", $($(this).data('stored-slot-id')), $(this).data('stored-interval-length'));

                    $.each($('.itinerary-item'), function() {
                        var thisId = $(this).data("stored-slot-id");

                        if(resizingSlotId != thisId) {
                            var thisSplit = thisId.split("-");
                            var thisIdentifier = thisSplit[1] + "-" + thisSplit[2] + "-" + thisSplit[3];
                            var resizingSplit = resizingSlotId.split("-");
                            var resizingIdentifier = resizingSplit[1] + "-" + resizingSplit[2] + "-" + resizingSplit[3];

                            if (thisIdentifier == resizingIdentifier &&
                                (resizingSplit[4] < thisSplit[4] || (resizingSplit[4] == thisSplit[4] && resizingSplit[5] < thisSplit[5]))

                            ) {

                                var difference = ((thisSplit[4] - resizingSplit[4]) * 4) + (thisSplit[5] - resizingSplit[5]);
                                difference = difference * INTERVAL_HEIGHT;
                                //console.log("difference: " + difference);
                                limits.push(difference);
                            }
                        }
                    });

                    resizingElement.resizable("option", "maxHeight", Math.min.apply(Math, limits));

                }
            }).resizable( "disable" );

            function getItemIntervalsAmount(item){
                return item.height() / INTERVAL_HEIGHT;
            }


            function attachDraggableTo(draggableElement, targetElement) {
                // firstly clear the currently stored ids of where elements are
                if(draggableElement.data("stored-slot-id")) {
                    var previousDroppable = draggableElement.data("stored-slot-id");
                    //console.log(draggableElement.data("stored-slot-id"));
                    $(previousDroppable).droppable("enable");
                    $(previousDroppable).data("stored-item-id", null);
                }

                console.log("HAVE YOU ACCOUNTED FOR INTERVAL LENGTH NOT GETTING CHANGED WHEN MOVING ITEM?");

                // store the IDs of itinerary item and droppable slot on eachother for reference later
                targetElement.data("stored-item-id", '#' + $(draggableElement).attr('id'));
                draggableElement.data("stored-slot-id", '#' + $(targetElement).attr('id'));

                // physically append the html element to the droppable element so it is styled correctly
                $(targetElement).append(draggableElement);

                // clear all current styling that we have attached so it has default behaviour
                draggableElement.removeClass("itinerary-item-placed offset-3 col-9");
                draggableElement.removeClass('itinerary-item-not-placed');
                draggableElement.removeAttr("style");
                draggableElement.css({
                    "top": "0",
                    "left": "0"});


                $.each($('.itinerary-item'), function() {
                  $(this).resizable("option", "maxHeight", null);
                });
            }

            $(".itinerary-item-close-button").click(function() {
                var itineraryItem = $(this).parent().parent();
                attachDraggableTo(itineraryItem, $("#itinerary-items-store"));
                itineraryItem.addClass('itinerary-item-not-placed');
                itineraryItem.data('stored-interval-length', '1');
            });

            $(".itinerary-item-expand-button").click(function() {
                expandItineraryItem($(this).parent().parent());
            });

            function SwitchSlots (operation, targetSlot, amount) {
                var firstSlotSplit = targetSlot.attr('id').split("-");

                for (count = Number(firstSlotSplit[5]); count < Number(amount) + Number(firstSlotSplit[5]); count++) {
                    var disableId = '#' + firstSlotSplit[0] + "-" + firstSlotSplit[1] + "-" + firstSlotSplit[2] + "-" + firstSlotSplit[3] + "-" + (firstSlotSplit[4]) + "-" + (count % 4);

                    $(disableId).droppable(operation);

                    if (count % 4 == 3) {
                        firstSlotSplit[4]++;
                    }
                }

            }

            $('.itinerary-droppable-slot, #itinerary-items-store').droppable({
                drop: function (event, ui) {
                    changesMade = true;
                    console.log("Changes made 2");

                    // store elements
                    var isItemStore = $(this).is("#itinerary-items-store");
                    var droppedElement = ui.draggable;

                    // re-enable old droppables
                    if($(droppedElement.data('stored-slot-id')).length) {
                        SwitchSlots("enable", $(droppedElement.data('stored-slot-id')), droppedElement.data('stored-interval-length'));
                    }

                    // disable revert so that we can reposition the element
                    // we will enable it once it is positioned
                    droppedElement.draggable("option", "revert", false);

                    // append the element and save the IDs
                    attachDraggableTo(droppedElement, $(this));

                    if(!isItemStore){
                        // disable the droppable as this time slot is now filled
                        $(this).droppable("disable")

                        // add item placed class so we can style items in the calendar differently
                        droppedElement.addClass("itinerary-item-placed offset-3 col-9");
                        droppedElement.resizable("enable")

                        SwitchSlots("disable", $(this), droppedElement.data('stored-interval-length'));

                    }
                    else
                    {
                        droppedElement.data('stored-interval-length', '1');
                        droppedElement.resizable("disable");
                        droppedElement.addClass('itinerary-item-not-placed');
                    }
                },
                over: function (event, ui) {
                    var draggingElement = ui.draggable;
                    draggingElement.draggable("option", "revert", true);

                },
                out: function (event, ui) {
                    var draggingElement = ui.draggable;
                }
            });

            function CalculateDaySliderMax(){

                if(listOfDays.length == 1){
                    return 1;
                }

                if(number_of_days_to_show == 1)
                    return listOfDays.length;

                return listOfDays.length - 2;
            }

            $( "#daySlider" ).slider({
                value: 1,
                min: 1,
                max: listOfDays.length - 2,
                step: 1,
                slide: function( event, ui ) {
                    ShowItineraryDays(ui.value, number_of_days_to_show);
                }
            });

            function ShowItineraryDays(day, numberOfDays){
                console.log("Show");
                $( "#daySelected" ).val( "Day: " + day );

                listOfDays.forEach(function (dayElement) { hideDay(dayElement) });

                console.log("Showing: " + numberOfDays);

                if(numberOfDays == 1)
                    showDay(listOfDays[day - 1]);
                else {
                    for (currentDay = day - (numberOfDays / 2); currentDay < day + (numberOfDays / 2); currentDay++) {
                        console.log(Math.round(currentDay));
                        showDay(listOfDays[Math.round(currentDay)]);
                    }
                }
            }

            $(function() {
                // if there aren't enough days to use the slider, hide it
                if(listOfDays.length <= 3){
                    $('#daySlider').hide();
                }
            })

            $( "#daySelected" ).val( "Day: " + $( "#daySlider" ).slider( "value" ) );


        } // end init()

    </script>
    <title>Itinerary</title>

</head>


<!-- used for detecting bootstrap page breaks in js.
could just test screen width but bootstrap is already doing it so lets just use this
also means our design will also break at the same point -->
<div class="device-xs visible-xs"></div>
<div class="device-sm visible-sm"></div>
<div class="device-md visible-md"></div>
<div class="device-lg visible-lg"></div>

<div class="col order-progress-image-wrapper">
    <img src="${pageContext.request.contextPath}/resources/images/Progress2.png" class="rounded" alt="progress" width="1000" height="94">
</div>

<div class="container">
    <div class="row mt-4 mb-4">
        <div id="daySlider"></div>
    </div>
    <div class="d-flex flex-row-reverse mt-2 mb-2">
        <input id="save-itinerary" type="button" class="btn btn-success" value="Save" />
        <input id="switch-itinerary-view" type="button" class="btn btn-info" value="Switch View" />
    </div>
    <s:iterator value="#session.PackageBean.itineraryEventsCart" var="iterEvents" step="1">
        <div class="event-item" data-stored-slot-id="<s:property value="formattedEventItemId"/>" data-stored-interval-length="<s:property value="eventIntervalAmount"/>">
            <button type="button" class="btn btn-success event-item-expand-button">
                <i class="fa fa-caret-right" aria-hidden="true"></i>
            </button>
            <div class="event-item-title"><s:property value="eventTitle"/></div>
            <div class="event-item-expand">
                <h5><s:property value="eventTitle"/></h5>
                <p><s:property value="eventDescription"/></p>
                <p>Price: $<s:property value="price"/></p>
                <p><s:property value="startDate"/> - <s:property value="endDate"/></p>

            </div>
        </div>
    </s:iterator>
    <div id="itinerary-wrapper" class="row">
        <div id="itinerary-items-store" class="col-2">
        <s:iterator value="#session.PackageBean.itineraryDealCart" var="iterDeals" step="1">
            <div class="card itinerary-item" id='<s:property value="dealID"/>' data-local-booking-id="-1" data-stored-slot-id="" data-stored-interval-length="1">
                <s:hidden cssClass="hidden-itinerary-item-id" name="itineraryItemId" value="" />
                <div class="itinerary-item-buttons-wrapper">
                    <button type="button" class="btn btn-success itinerary-item-expand-button"><i class="fa fa-caret-down" aria-hidden="true"></i></button>
                    <button type="button" class="btn btn-danger itinerary-item-close-button">X</button>
                </div>
                <div class="card-block">
                    <p class="itinerary-card-block-p"><s:property value="business.businessName"/></p>
                </div>
                <div class="itinerary-item-expand">
                    <s:property value="dealTitle"/>
                    <s:textfield type="number" label="Adults (18+)" min="0" class="numberOfAdults" name="numberOfAdults" value="%{numberOfAdults}" />
                    <s:textfield type="number" label="Children" min="0" class="numberOfChildren" name="numberOfChildren" value="%{numberOfChildren}" />
                </div>
            </div>
        </s:iterator>

        <s:iterator value="#session.PackageBean.bookings" var="iterBookings" step="1">
            <div class="card itinerary-item" id='<s:property value="deal.dealID"/>' data-local-booking-id="<s:property value="localBookingID"/>" data-stored-slot-id="<s:property value="formattedItineraryItemId"/>" data-stored-interval-length="<s:property value="itineraryIntervalAmount"/>">
                <s:hidden cssClass="hidden-itinerary-item-id" name="itineraryItemId" value="" />
                <div class="itinerary-item-buttons-wrapper">
                    <button type="button" class="btn btn-success itinerary-item-expand-button"><i class="fa fa-caret-down" aria-hidden="true"></i></button>
                    <button type="button" class="btn btn-danger itinerary-item-close-button">X</button>
                </div>
                <div class="card-block">
                    <p class="itinerary-card-block-p"><s:property value="deal.business.businessName"/></p>
                </div>
                <div class="itinerary-item-expand">
                    <s:property value="deal.dealTitle"/>
                    <s:textfield type="number" label="Adults (18+)" min="0" class="numberOfAdults" name="numberOfAdults" value="%{numberOfAdults}" />
                    <s:textfield type="number" label="Children" min="0" class="numberOfChildren" name="numberOfChildren" value="%{numberOfChildren}" />
                </div>
            </div>
        </s:iterator>
        </div>
        <div id="itineraryArea" class="itinerary-target-area col row">
        <s:iterator value="#session.PackageBean.itineraryDays" var="iterDays" step="1">
            <s:hidden cssClass="itineraryDaysHidden" id="%{iterDays}" />
        </s:iterator>
        </div>
    </div>
</div>