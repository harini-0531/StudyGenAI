// 1. SET YOUR BACKEND URL HERE
const API_BASE_URL = "https://studygenai-backend.onrender.com"; 
const FRONTEND_URL = window.location.origin; // Automatically gets https://studygenai-h8a7.onrender.com

console.log("JavaScript is working!");


// Upload PDF Function

function uploadPDF() {

    const fileInput = document.getElementById("pdfFile");
    const file = fileInput.files[0];

    // No file selected
    if (!file) {

        alert("Please select a PDF file.");
        return;

    }

    // Create form data
    const formData = new FormData();
    formData.append("file", file);


    // Call Spring Boot API

    fetch("https://studygenai-backend.onrender.com/api/upload", {

        method: "POST",
        body: formData

    })

    .then(response => {

        if (!response.ok) {
            throw new Error("Upload failed.");
        }

        return response.text();

    })

    .then(data => {

         console.log("Redirecting...");

    setTimeout(() => {
        window.location.href = "/summary.html";
    }, 1000);


    })

    .catch(error => {

        console.error(error);
        alert("PDF Upload Failed.");

    });

}
// Generate Summary

function generateSummary() {

    const summaryType =
        document.getElementById("summaryType").value;

<<<<<<< HEAD
     fetch(`https://studygenai-backend.onrender.com/api/latest-document`)
=======
   fetch(`${https://studygenai-backend.onrender.com}/api/latest-document`)
>>>>>>> d89352a3400cac9e28154f1c355085efffbf5acd

        .then(response => response.json())

        .then(id => {

<<<<<<< HEAD
           return fetch(`https://studygenai-backend.onrender.com/api/summary/${id}?type=${summaryType}`);
=======
           return fetch(`${https://studygenai-backend.onrender.com}/api/summary/${id}?type=${summaryType}`);
>>>>>>> d89352a3400cac9e28154f1c355085efffbf5acd

        })

        .then(response => response.json())

        .then(data => {

            document.getElementById("summaryOutput")
                    .innerText = data.summary;

        })

        .catch(error => {

            console.error(error);

            alert("Summary generation failed.");

        });
}


// Generate Study Plan

function generateStudyPlan() {

    const subjects =
        document.getElementById("subjects").value;

    const studyHours =
        document.getElementById("studyHours").value;

    const examDate =
        document.getElementById("examDate").value;
    const preferredTime =
document.getElementById("preferredTime").value;

    fetch(
<<<<<<< HEAD
    `https://studygenai-backend.onrender.com/api/planner?subjects=${encodeURIComponent(subjects)}&studyHours=${studyHours}&examDate=${examDate}&preferredTime=${preferredTime}`,
=======
    `${https://studygenai-backend.onrender.com}/api/planner?subjects=${encodeURIComponent(subjects)}&studyHours=${studyHours}&examDate=${examDate}&preferredTime=${preferredTime}`,
>>>>>>> d89352a3400cac9e28154f1c355085efffbf5acd
    {
        method: "POST"
    }
)

    .then(response => response.text())

    .then(data => {

        document.getElementById("plannerOutput")
                .innerText = data;

    })

    .catch(error => {

        console.error(error);

        alert("Failed to generate study plan.");

    });

}
function downloadSummary() {

    const summary =
        document.getElementById("summaryOutput").innerText;

    const { jsPDF } = window.jspdf;

    const doc = new jsPDF();

    doc.setFontSize(16);
    doc.text("StudyGen AI - Summary", 10, 10);

    doc.setFontSize(12);

    const lines =
        doc.splitTextToSize(summary, 180);

    doc.text(lines, 10, 25);

    doc.save("StudyGenAI_Summary.pdf");
}
//Download studyplan
function downloadStudyPlan() {

    const plan =
        document.getElementById("plannerOutput").innerText;

    const { jsPDF } = window.jspdf;

    const doc = new jsPDF();

    doc.setFontSize(16);
    doc.text("StudyGen AI - Study Plan", 10, 10);

    doc.setFontSize(12);

    const lines =
        doc.splitTextToSize(plan, 180);

    doc.text(lines, 10, 25);

    doc.save("StudyGenAI_StudyPlan.pdf");
}

let correctAnswers = [];
let currentScore = 0;


function generateQuiz() {

    const questionCount =
        document.getElementById("questionCount").value;

    const difficulty =
        document.getElementById("difficulty").value;

<<<<<<< HEAD
   fetch(`https://studygenai-backend.onrender.com/api/latest-document`)
=======
   fetch(`${https://studygenai-backend.onrender.com}/api/latest-document`)
>>>>>>> d89352a3400cac9e28154f1c355085efffbf5acd

    .then(response => response.json())

    .then(id => {

<<<<<<< HEAD
         return fetch(`https://studygenai-backend.onrender.com/api/quiz/${id}?count=${questionCount}&difficulty=${difficulty}`);
=======
       return fetch(`${https://studygenai-backend.onrender.com}/api/quiz/${id}?count=${questionCount}&difficulty=${difficulty}`);
    })

>>>>>>> d89352a3400cac9e28154f1c355085efffbf5acd
    })


    .then(response => response.json())

    .then(data => {

        let html = "";

        correctAnswers = [];

        data.questions.forEach((q, index) => {

            correctAnswers.push(q.answer);

            html += `
                <div class="card mb-4 shadow-sm">

                    <div class="card-body">

                        <h5>
                            Question ${index + 1}
                        </h5>

                        <p>
                            ${q.question}
                        </p>

                        <div class="form-check">
                            <input type="radio"
                                   class="form-check-input"
                                   name="q${index}"
                                   value="A">
                            <label>${q.optionA}</label>
                        </div>

                        <div class="form-check">
                            <input type="radio"
                                   class="form-check-input"
                                   name="q${index}"
                                   value="B">
                            <label>${q.optionB}</label>
                        </div>

                        <div class="form-check">
                            <input type="radio"
                                   class="form-check-input"
                                   name="q${index}"
                                   value="C">
                            <label>${q.optionC}</label>
                        </div>

                        <div class="form-check">
                            <input type="radio"
                                   class="form-check-input"
                                   name="q${index}"
                                   value="D">
                            <label>${q.optionD}</label>
                        </div>

                    </div>

                </div>
            `;
        });

        document.getElementById("quizOutput").innerHTML = html;

    })

    .catch(error => {

        console.error(error);

        alert("Quiz generation failed: " + error);

    });
}
// Ask AI

function askAI() {

    const question =
        document.getElementById("questionInput").value;

<<<<<<< HEAD
    fetch(`https://studygenai-backend.onrender.com/api/chat/1`, {
=======
   fetch(`${https://studygenai-backend.onrender.com}/api/chat/1`, {
>>>>>>> d89352a3400cac9e28154f1c355085efffbf5acd
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify({
        question: question
<<<<<<< HEAD
=======
    })
})

        body: JSON.stringify({
            question: question
        })

>>>>>>> d89352a3400cac9e28154f1c355085efffbf5acd
    })
})

    .then(response => response.json())

    .then(data => {

        document.getElementById("chatOutput")
                .innerText = data.answer;

    })

    .catch(error => {

        console.error(error);

        alert("AI response failed.");

    });

}
function clearChat() {

    document.getElementById("questionInput").value = "";

    document.getElementById("chatOutput")
            .innerText =
            "The AI response will be displayed here.";

}
function submitQuiz() {

    let score = 0;

    for(let i=0;i<correctAnswers.length;i++) {

        const selected =
            document.querySelector(
                `input[name="q${i}"]:checked`
            );

        if(selected &&
           selected.value === correctAnswers[i]) {

            score++;
        }
    }

    currentScore = score;

    document.getElementById("scoreOutput")
            .innerText =
            "Quiz Submitted!";
}

function viewScore() {

    document.getElementById("scoreOutput")
            .innerText =
            "Your Score: "
            + currentScore
            + "/"
            + correctAnswers.length;
}
// Generate Flashcards
function generateFlashcards() {

    const count =
        document.getElementById("flashcardCount").value;

<<<<<<< HEAD
    fetch(`https://studygenai-backend.onrender.com/api/latest-document`)
=======
    fetch(`${https://studygenai-backend.onrender.com}/api/latest-document`)
>>>>>>> d89352a3400cac9e28154f1c355085efffbf5acd

    .then(response => response.json())

    .then(id => {

<<<<<<< HEAD
    return fetch(`https://studygenai-backend.onrender.com/api/flashcards/${id}?count=${count}`);

=======
        return fetch(`${https://studygenai-backend.onrender.com}/api/flashcards/${id}?count=${count}`);
>>>>>>> d89352a3400cac9e28154f1c355085efffbf5acd
    })

    .then(response => response.json())

    .then(data => {

        flashcards = data.flashcards;

        console.log(flashcards);
        console.log(flashcards.length);

        currentCard = 0;
        showAnswer = false;

        showCard();
    })

    .catch(error => {

        console.error(error);

        alert("Flashcard generation failed.");

    });
}
function showCard() {

    const parts =
        flashcards[currentCard].split("|");

    const question =
        parts[0] ? parts[0].trim() : "";

    const answer =
        parts[1] ? parts[1].trim() : "";

    document.getElementById("flashcardOutput")
        .innerHTML = `

        <div class="card shadow-lg border-primary">

            <div class="card-header bg-primary text-white text-center">

                Flashcard ${currentCard + 1}
                of ${flashcards.length}

            </div>

            <div class="card-body p-5">

                <h4 class="text-primary">

                    Question

                </h4>

                <p class="fs-5">

                    ${question}

                </p>

                <hr>

                <h4 class="text-success">

                    Answer

                </h4>

                <p class="fs-5">

                    ${answer}

                </p>

            </div>

        </div>
    `;
}

function nextCard() {

    if(currentCard < flashcards.length - 1) {

        currentCard++;

        showCard();

    }

}
function previousCard() {

    if(currentCard > 0) {

        currentCard--;

        showCard();

    }

}
