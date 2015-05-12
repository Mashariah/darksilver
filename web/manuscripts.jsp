<%-- 
    Document   : manuscripts
    Created on : Feb 28, 2015, 9:16:25 PM
    Author     : kelli
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Trace Home</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/institutes.js"></script>
        <%@include file="templates/cheader.html" %>
        <link rel="stylesheet" type="text/css" href="styler/menu.css"  />
        <link rel="stylesheet" type="text/css" href="styler/design.css"  />
        <%@include  file="templates/main-menu.html" %>

    </head>
    <body>
        <div class="manuscripts-menu-bar">
            <div class="author-menu-item"><a href="new_author.jsp">New</a></div>
            <div class="author-menu-item"><a href="#">Edit</a></div>
            <div class="author-menu-item"><a href="#">Details</a></div>
            <div class="author-menu-item"><a href="#">Delete</a></div>

            <!-- reduced the top margin of form because of the bar div -->            
            <form  style="margin-top:-20px;" action="search_m" method="GET">
                <input type="hidden" name="search_type" value="1"/>
                <input type="text" class="search-input" name="txt_search" value="" />
                <input type="submit"  class="search-btn" value="Search" />
            </form>
        </div>
        <div class="manuscripts-filter-panel">
            <h3>Manuscripts Filter</h3>
            Year<br>
            <select  style="width: 43%;" id="opt_year_from">
                <option value="1995">1995</option>
                <option value="1996">1996</option>
                <option value="1997">1997</option>
            </select>  - 
            <select style="width: 43%;" id="opt_year_to" name="opt_year_to" onchange="doManuscriptsFilter(this)">
                <option value="1995">1995</option>
                <option value="1996">1996</option>
                <option value="1997">1997</option>
            </select>

            <br>Status<br>
            <select id="status" name="status" size="1" onchange="doManuscriptsFilter(this)">
                <option value="received">Received</option>
                <option value="in_review">In-Review</option>
                <option value="in_correction">In-Correction</option>
                <option value="accepted">Accepted</option>
                <option value="rejected">Rejected</option>
                <option value="withdrawn">Withdrawn</option>
                <option value="waitlisted">Wait-listed</option>
                <option value="published">Published</option>
            </select>
            <br>Affiliation<br>
            <select id="affiliation" name="affiliation" size="1" onchange="doManuscriptsFilter(this)">
                <!--create the list of institutions from the JSON file in the templates file -->
                <script>
                    var listOfInstitutes = JSON.parse(institutes);
                    var selectorObj = document.getElementById("affiliation");
                    for (i = 0; i < listOfInstitutes.length; i++) {
                        var option = document.createElement("option");
                        var optionText = document.createTextNode(listOfInstitutes[i].iname);
                        option.id = listOfInstitutes[i].iname;
                        option.value = listOfInstitutes[i].iname;
                        option.appendChild(optionText);
                        selectorObj.appendChild(option);
                        console.log(listOfInstitutes[i].iname);
                    }
                </script>
            </select>
            <br>Keywords<br>
            <input   type="text" name="keywords" id="keywords" onkeyup="doManuscriptsFilter(this)" />
        </div>
        <!--<div class="output-section">-->
        <table id="tbl_results">
            <tbody id="m_tbody">
                <!--check if the manuscripts list is null...report no results found is true-->
                <c:forEach  items="${m_list}" var="manuscript" end="14">
                    <tr>
                        <td id="chk_box"><input type="checkbox" name="action" value="OFF" /></td>
                        <td id="m_ref_no"><c:out value="${manuscript.manuscriptRefNumber}"></c:out></td>
                        <td id="m_title" style="width: 600px; "><c:out value="${manuscript.title}"></c:out></td>
                            <td style="text-align: right;" id="m_others">
                            <c:out value="${manuscript.authorId.surname}, ${manuscript.authorId.initials}."></c:out>
                            <br><c:out value="${manuscript.status}"></c:out>
                            </td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
        <!--</div>-->
        
        <div class="pagination-panel" id="page-panel"> <%-- previous link exept for first page--%>
            <span id="page_details"><c:out value="Page ${currentPage} of ${totalPages} "></c:out></span>
                <!-- add all icons for page navigation...activate based on navigation-->
                <img src="resc/icons/pagination-panel/inactive/ico_first.png" alt="first" id="nav_ico_first"/>            
                <img src="resc/icons/pagination-panel/inactive/ico_prev.png" alt="prev" id="nav_ico_prev"/>            
                <img src="resc/icons/pagination-panel/inactive/ico_next.png" alt="next" id="nav_ico_next"/>            
                <img src="resc/icons/pagination-panel/inactive/ico_last.png" alt="last" id="nav_ico_last"/> 
                
            <c:if test="${currentPage != 1}">
                <script>
                    //replace  first and prev icons to active state
                    var icon_prev = document.getElementById("nav_ico_prev");
                    var icon_first= document.getElementById("nav_ico_first");
                    icon_prev.src = "resc/icons/pagination-panel/activer/ico_prev.png";
                    icon_first.src = "resc/icons/pagination-panel/activer/ico_first.png";
                    //create link using icons as background...
                    var prev_link = document.createElement("a"); prev_link.id = "lnk_prev";
                    var first_link = document.createElement("a"); first_link.id ="lnk_first";
                    //set page references on links
                    prev_link.href = "mlist.sx?page=${currentPage -1}";
                    first_link.href = "mlist.sx?page=1";
                    prev_link.appendChild(icon_prev);
                    first_link.appendChild(icon_first);
                    var target_div = document.getElementsByClassName("pagination-panel")[0];
                    target_div.insertBefore(prev_link, document.getElementById("nav_ico_next"));
                    target_div.insertBefore(first_link,document.getElementById("nav_ico_prev"));
                    
               </script>
            </c:if>
           <c:if test="${currentPage lt  totalPages}" >
               <script>
                   //activate the next and last page links
                    var icon_next = document.getElementById("nav_ico_next");
                    var icon_last= document.getElementById("nav_ico_last");
                    icon_next.src = "resc/icons/pagination-panel/activer/ico_next.png";
                    icon_last.src = "resc/icons/pagination-panel/activer/ico_last.png";
                    var next_link = document.createElement("a"); next_link.id ="lnk_next";
                    var last_link = document.createElement("a");last_link.id ="lnk_last";
                    next_link.href = "mlist.sx?page=${currentPage + 1}";
                    last_link.href = "mlist.sx?page=${currentPage + (totalPages - currentPage)}";
                    next_link.appendChild(icon_next);
                    last_link.appendChild(icon_last);
                    var target_div = document.getElementsByClassName("pagination-panel")[0];
                    console.log(target_div.innerHTML);
                    target_div.insertBefore(next_link, document.getElementById("nav_ico_last"));
                    target_div.appendChild(last_link);
               </script>
            </c:if> 
        </div>

        <script>

            var totalResults;
            var totalPages;
            var RESULTS_PER_PAGE = 10;
            var filterResultsCache;

            var targetFilter;
            var userChoice;
            var page = 1;
            var dateFrom;
            var dateTo;

            /**
             * Get a reference to the filter element used and the criteria selected
             * @param {type} filterObj element the user is basing the filtering on
             * @returns {undefined} 
             */
            function doManuscriptsFilter(filterObj) {
                page = 1;                //initialise or reset page 
                console.log("The selected filter element is:" + filterObj.name);
                targetFilter = filterObj.name;
                if (targetFilter === "status" || targetFilter === "affiliation") {
                    userChoice = filterObj.options[filterObj.selectedIndex].value;
                } else if (targetFilter === "keywords") {
                    userChoice = filterObj.value;
                    console.log("User choice: " + userChoice);
                } else if (targetFilter === "opt_year_to") {
                    //get the to and from dates
                }
                getFilterResults(page);
            }

            /**
             * Use ajax call to get the results of the filter as defined by the parameters supplied
             * @returns {undefined}
             */
            function getFilterResults(count) {
                var xmlhttp;
                console.log("in filterResults, page =" + page);
                var url = "/trace/search_m?search_type=2&filter_criteria=" + userChoice + "&filter_target=" + targetFilter + "&page=" + count;
                //code for IE7+, Chrome, Firefox, Opera
                console.log("URL = " + url);
                if (window.XMLHttpRequest) {
                    xmlhttp = new XMLHttpRequest();
                }
                //code for IE5 and IE6
                else {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function() {
                    if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                        var formatted_res;
                        formatted_res = xmlhttp.responseText.replace(/\n/gm, "<br/>");
//                        console.log("response from server= "+formatted_res);
                        filterResultsCache = JSON.parse(formatted_res); //create a JSON array from the response text
                        totalResults = filterResultsCache.length; //get the results count to determine last batch...
                        page = count;//set the page to the currently navigated page.
                        formatAndDisplay();
                    }
                }
                xmlhttp.open("GET", url, true);
                xmlhttp.send();
            }

            /**
             * 1. Loop through the table nodes of the DOM
             2. Update the contents of the table cells using  the id of each table cell.
             parse the JSON string and format for the table display
             */
            function formatAndDisplay() {
                //get references to the existing results table
                var tbl = document.getElementById("tbl_results");
                var tbody = document.createElement("tbody");

                //remove all current sub nodes of the table....clear existing data.
                clear_contents(tbl);
                //loop through all objects in the json array..show msg and exit
                if (filterResultsCache.length < 1) {
                    var row = document.createElement("tr");
                    var cell = document.createElement("td");
                    var text = "No Results  Found!";
                    cell.appendChild(document.createTextNode(text));
                    row.appendChild(cell);
                    tbody.appendChild(row);
                    tbl.appendChild(tbody);
                    return;
                }

                for (i = 0; i < filterResultsCache.length; i++) {
                    var currentObj = filterResultsCache[i];
                    console.log("Item reference: " + i + " in array = " + filterResultsCache[i].ref_no);

                    //create a row for each element in the array...and populate with data
                    var row = document.createElement("tr");
                    //for each row populate with array values using table cells...
                    for (var key in currentObj) {
                        if (currentObj.hasOwnProperty(key)) {
                            var buffer;
                            buffer = document.createTextNode(currentObj[key]);
                            var cell = document.createElement("td");
                            cell.appendChild(buffer);
                            row.appendChild(cell);
                        }
                    }
                    tbody.appendChild(row);
                }
                tbl.appendChild(tbody);
                //increment the page count
                addNavButtons();
            }

            /**
             *   Reset the table for fresh results..
             * @param {type} table
             * @returns {undefined}
             */
            function clear_contents(table) {
//                var tbody = document.getElementById("m_tbody");
                var tbody = document.getElementsByTagName("tbody")[0];
                if (tbody !== null) {
                    if (tbody.hasChildNodes()) {
                        table.removeChild(tbody);
                    }
                    console.log("tbody tag found and cleared....");
                } else {
                    console.log("tbody tag is not found....");
                }
            }


            function addNavButtons() {

                var nextFunction = "javascript:getFilterResults(" + (page + 1) + ")";
                var prevFunction = "javascript:getFilterResults(" + (page - 1) + ")";

                //update the pagination panel to use javascript functions
                var details_span = document.getElementById("page_details");
                details_span.innerHTML ="Page "+page+" of Results";
                
                //activate fist and prev buttons
                    var lnk_next= document.getElementById("nav_ico_next");
                    console.log(lnk_next);
                    var lnk_prev= document.getElementById("nav_ico_prev");
                    console.log(lnk_prev);
                    lnk_prev.href = prevFunction;
                    lnk_next.href = nextFunction;
           
                
            }
        </script>
    </body>
</html>
