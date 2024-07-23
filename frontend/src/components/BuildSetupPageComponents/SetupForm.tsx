import React from "react";

function SetupForm() {

    function setSuccess(field: HTMLElement): void {
        const parentNode = field.parentNode;
        if(parentNode)
        {
            const errorDiv = parentNode.querySelector(".BuildSetupPage_error-div");
            field.style.border = "2px solid green";

            if(errorDiv)
                errorDiv.textContent = "";
        }
    }

    function setError(field: HTMLElement, message: string): void {
        const parentNode = field.parentNode;
        if(parentNode)
        {
            const errorDiv = parentNode.querySelector(".BuildSetupPage_error-div") as HTMLElement;
            field.style.border = "2px solid red";
            if(errorDiv)
            {
                errorDiv.style.color = "#ff3a3a";
                errorDiv.textContent = message;
            }
        }
    }

    function validateNameField(): boolean {
        const nameField = document.getElementById("BuildSetupPage_form-name") as HTMLInputElement;

        let name = nameField.value;
        let len = name.length;

        if (name === "")
            return true

        if (len > 50)
        {
            setError(nameField, "Build name cannot exceed 50 characters.");
            return false;
        }
        else
        {
            setSuccess(nameField);
            return true;
        }
    }

    function validateBudgetField(): boolean {
        const budgetField = document.getElementById("BuildSetupPage_form-budget") as HTMLInputElement;
        let budget = budgetField.value;

        if (budget === "")
            return true;

        else
        {
            if (!isNaN(Number(budget)) && !isNaN(parseFloat(budget)))
            {
                const nonNumericRegex = /[^0-9.]/;
                if (nonNumericRegex.test(budget))
                {
                    setError(budgetField, "Invalid budget. Please enter only numeric values.");
                    return false;
                }

                setSuccess(budgetField);
                return true;
            }
            setError(budgetField, "Invalid budget. Please enter only numeric values.");
            return false;
        }
    }

    function validateDescriptionField(): boolean {
        const descriptionField = document.getElementById("BuildSetupPage_form-description") as HTMLInputElement;
        let description = descriptionField.value;

        if (description === "")
            return true;

        let len = description.length;
        if (len > 400)
        {
            setError(descriptionField, "Description cannot exceed 400 characters.");
            return false;
        }
        else
        {
            setSuccess(descriptionField);
            return true;
        }
    }

    function handleSubmit(event: React.ChangeEvent<HTMLFormElement>): void {
        event.preventDefault();

        const isNameValid = validateNameField();
        const isBudgetValid = validateBudgetField();
        const isDescriptionValid = validateDescriptionField();

        if (isNameValid && isBudgetValid && isDescriptionValid)
        {
            const form = document.querySelector(".BuildSetupPage_form") as HTMLFormElement;

            const buildNameInput = form.querySelector("#BuildSetupPage_form-name") as HTMLInputElement;
            const buildName = buildNameInput.value;

            const budgetInput = form.querySelector("#BuildSetupPage_form-budget") as HTMLInputElement;
            const budget : number = parseFloat(budgetInput.value);

            const descriptionInput = form.querySelector("#BuildSetupPage_form-description") as HTMLInputElement;
            const description = descriptionInput.value;

            interface FormData {
                buildName: string | null,
                budget: number | null,
                description: string | null
            }

            const formData: FormData = {
                buildName: buildName.trim() === "" ? null : buildName as string,
                budget: budget,
                description: description.trim() === "" ? null : description
            };
            
            const params: RequestInit = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData),
                credentials: "include"
            };

            fetch('http://localhost:8081/api/storeBuildData', params)
                .then(response => {
                    if (!response.ok)
                        throw new Error('Network response was not ok');

                    form.reset();
                    window.location.href = '/build';

                })
                .catch(error => {
                    console.error('Could not POST form data to http://localhost:8081/api/storeBuildData:', error);
                });
        }
    }

    return (
        <div className="container BuildSetupPage_form-container mt-5">
            <Header/>
            <form className="BuildSetupPage_form" id="buildSetupForm" onSubmit={handleSubmit}>
                <div className="BuildSetupPage_input-field">
                    <input type="text" id="BuildSetupPage_form-name" className="BuildSetupPage_field form-control" placeholder="Build Name" onChange={validateNameField} name="buildName"/>
                    <div className="BuildSetupPage_error-div" id="BuildSetupPage_name-error"></div>
                </div>

                <div className="BuildSetupPage_input-field">
                    <input type="text" id="BuildSetupPage_form-budget" className="BuildSetupPage_field form-control" placeholder="$ Budget (optional)" onChange={validateBudgetField} name="budget"/>
                    <div className="BuildSetupPage_error-div" id="BuildSetupPage_budget-error"></div>
                </div>

                <div className="BuildSetupPage_input-field">
                    <textarea id="BuildSetupPage_form-description" className="BuildSetupPage_field form-control" placeholder="Description (optional)" onChange={validateDescriptionField} name="description"/>
                    <div className="BuildSetupPage_error-div" id="BuildSetupPage_description-error"></div>
                </div>
                <SubmitButton/>
            </form>
        </div>
    );
}

function Header() {
    return (
        <div className="BuildSetupPage_header-container">
            <div className="BuildSetupPage_title-container container-fluid d-flex flex-row justify-content-center my-4">
                <h1 className="display-5 BuildSetupPage_title">Build Setup</h1>
            </div>
            <Description/>
        </div>
    );
}

function Description() {
    return (
        <div className="BuildSetupPage_description-container container-fluid d-flex flex-row justify-content-center mb-3">
            <p className="BuildSetupPage_description">Your Ultimate PC Awaits</p>
        </div>
    );
}

function SubmitButton() {
    return (
        <div className="BuildSetupPage_btn-container">
            <button type="submit" className="btn btn-primary">Submit</button>
        </div>
    );
}

export default SetupForm;