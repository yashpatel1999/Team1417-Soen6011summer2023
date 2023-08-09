const params = new URLSearchParams(window.location.search);
console.log(params);
var id = params.get("id");
console.log(id);
let data = new Object();
// console.log(data)
// data.id=id;
let url = "http://localhost:9997/getAllJobs?id=" + id;
console.log(url);
// $(".empty-screen").html = "";
fetch(url, {
    method: 'POST',
    body: JSON.stringify(data),
}).then((response) => response.json())
    .then((data) => {
        console.log(data)
        var data1 = data.Job_details;
        const jobsHeading = document.querySelector(".jobs-list-container h2");
        // const jobsContainer = document.querySelector(".jobs-list-container .jobs");
        const jobSearch = document.querySelector(".jobs-list-container .job-search");
        let searchTerm = "";
        console.log(data1);
        var data2 = data.jobIds;

        if(data1.length>0)
        {
            jobsHeading.innerHTML = `${data1.length} Jobs`;
        }
        function createJobPostingCard(jobPosting) {
            let jobCard = document.createElement("div");
            jobCard.classList.add("job");

            let image = document.createElement("img");
            image.src = "images/software-engineer.svg";

            let title = document.createElement("h3");
            title.innerHTML = jobPosting.job_title;
            title.classList.add("job-title");

            let company_name = document.createElement("div");
            // console.log(job.company_name);
            company_name.innerHTML = jobPosting.company_name;
            company_name.classList.add("cName");

            let company_location = document.createElement("h4");
            company_location.innerHTML = jobPosting.company_location;
            company_location.classList.add("cLoc");

            let details = document.createElement("div");
            details.innerHTML = jobPosting.job_desc;
            details.classList.add("details");

            let applyButton = document.createElement("a");
            let deadline = jobPosting.deadline;
            let currentDate = new Date();
            if(checkJobId(jobPosting)&&compareDates(deadline,currentDate))
            {
                jobCard.style.background = "lightblue";
                applyButton.innerHTML = "Already Applied and Deadline passed";
                applyButton.classList.add("details-btn1");
            }
            else {
                if (checkJobId(jobPosting))
                {
                    jobCard.style.background = "lightgreen";
                    applyButton.innerHTML = "Already Applied";
                    applyButton.classList.add("details-btn1");
                }
                else if (compareDates(deadline,currentDate))
                {
                    jobCard.style.background = "lightpink";
                    applyButton.innerHTML = "Deadline passed";
                    applyButton.classList.add("details-btn1");
                }
                else {
                    applyButton.innerHTML = "Apply Here";
                    applyButton.classList.add("details-btn");
                    applyButton.addEventListener("click", () => {
                        redirectToJobPage(jobPosting.job_id);
                    });
                }
            }



            jobCard.appendChild(image);
            jobCard.appendChild(company_name);
            jobCard.appendChild(company_location);
            jobCard.appendChild(title);
            jobCard.appendChild(details);
            jobCard.appendChild(applyButton);

            return jobCard;
        }

        function redirectToJobPage(jobId) {
            th:window.location.href = `http://localhost:9997/getSingleJob?id=${jobId}&cid=`+id;
            console.log(jobId);

        }

        function displayJobPostings(jobPostings) {
            const jobPostingsContainer = document.querySelector(".jobs-list-container .jobs");


            jobPostingsContainer.innerHTML = "";

            jobPostings.forEach((jobPosting) => {
                    const jobCard = createJobPostingCard(jobPosting);
                    jobPostingsContainer.appendChild(jobCard);
            });
        }

        jobSearch.addEventListener("input", (e) => {
            searchTerm = e.target.value;
            console.log(searchTerm);
            handleSearch(data1);
        });
        function handleSearch(jobPostings)
        {
            console.log("here in handle search")
            const filteredJobs = jobPostings.filter(job =>
                job.job_title.toLowerCase().includes(searchTerm));
            displayJobPostings(filteredJobs);
        }

        function checkJobId(jobPostings)
        {
            for(let i=0;i<data2.length;i++)
            {
                if(jobPostings.job_id == data2[i].job_id)
                {
                    return true;
                }
            }
            return false;
        }

        function compareDates(d1, d2){
            let deadline = new Date(d1).getTime();
            let currentdate = new Date(d2).getTime();

            if (deadline < currentdate) {
                return true;
            }
            return false;
        }
        displayJobPostings(data1);


        const buttons = document.querySelectorAll('.nav-btn');
        const contents = document.querySelectorAll('.slide');

        buttons.forEach(button => {
            button.addEventListener('click', () => {
                // Remove active class from all buttons
                buttons.forEach(btn => {
                    btn.classList.remove('active');
                });

                // Add active class to the clicked button
                button.classList.add('active');

                // Hide all content divs
                contents.forEach(content => {
                    content.style.display = 'none';
                });

                // Show the corresponding content based on the clicked button
                const contentId = button.id.replace('button', 'content');
                const contentToShow = document.getElementById(contentId);
                contentToShow.style.display = 'block';
            });
        });

    });

let dataJob = new Object();
let urlJob = "http://localhost:9997/getAppliedJobs?id=" + id;
fetch(urlJob, {
    method: 'POST',
    body: JSON.stringify(dataJob),
}).then((response) => response.json())
    .then((dataJob) =>{
        console.log(dataJob);
        var data1 = dataJob.job_details;
        const tableBody = document.querySelector("#candidate-table1 tbody");
        console.log("till here1");
        data1.forEach(job => {
            const row = createJobRow(job);
            tableBody.appendChild(row);
        });
        function createJobRow(job) {
            const row = document.createElement("tr");

            const idCell = document.createElement("td");
            idCell.textContent = job.job_id;
            row.appendChild(idCell);

            const companyLocationCell = document.createElement("td");
            companyLocationCell.textContent = job.company_location;
            row.appendChild(companyLocationCell);

            const companyNameCell = document.createElement("td");
            companyNameCell.textContent = job.company_name;
            row.appendChild(companyNameCell);

            const jobDescCell = document.createElement("td");
            jobDescCell.textContent = job.job_desc;
            row.appendChild(jobDescCell);

            const jobTitleCell = document.createElement("td");
            jobTitleCell.textContent = job.job_title;
            row.appendChild(jobTitleCell);

            const postedCell = document.createElement("td");
            postedCell.textContent = job.job_posted;
            row.appendChild(postedCell);

            return row;

        }
    });

let dataSelectedJob = new Object();
let urlJobSelected = "http://localhost:9997/getSelectedJobs?id=" + id;
fetch(urlJobSelected, {
    method: 'POST',
    body: JSON.stringify(dataSelectedJob),
}).then((response) => response.json())
    .then((dataSelectedJob) =>{
        console.log(dataSelectedJob);
        var data1 = dataSelectedJob.job_details;
        const tableBody = document.querySelector("#candidate-table2 tbody");
        console.log("till here1");
        data1.forEach(job => {
            const row = createJobRow(job);
            tableBody.appendChild(row);
        });
        function createJobRow(job) {
            const row = document.createElement("tr");

            const idCell = document.createElement("td");
            idCell.textContent = job.job_id;
            row.appendChild(idCell);

            const companyLocationCell = document.createElement("td");
            companyLocationCell.textContent = job.company_location;
            row.appendChild(companyLocationCell);

            const companyNameCell = document.createElement("td");
            companyNameCell.textContent = job.company_name;
            row.appendChild(companyNameCell);

            const jobDescCell = document.createElement("td");
            jobDescCell.textContent = job.job_desc;
            row.appendChild(jobDescCell);

            const jobTitleCell = document.createElement("td");
            jobTitleCell.textContent = job.job_title;
            row.appendChild(jobTitleCell);

            const postedCell = document.createElement("td");
            postedCell.textContent = job.job_posted;
            row.appendChild(postedCell);

            return row;

        }
    });
