package org.example;

public class ResultsExpected {
    private static final String[] outputs = new String[21];

    static {
        // Initialize the array with expected outputs
        outputs[0] = "Parents: For children 13 years old and younger I REMEMBERED TO: HOME RULE CHECKLIST Show respect to my parent(s) and family members Greet my parent(s) when they or I enter/leave the house Maintain a good relationship with my brother or sisters Excellent Good Poor";
        outputs[1] = "Item Service Product Discount Description Example of service in industry Example of product in industry Example of discount in industry Unit Price 25.00 500.00 -100.00 Quantity 4.00 1.00 1.00 Amount 100.00 500.00 -100.00";
        outputs[2] = "Date: Order #: ,202 Customer Receipt Order Type Write a check payable to Unsun Chang\". Pictures will be delivered to the school Payment Total Paid S.K. Image: (213)S03-7470 unsunc@gmail.com";
        outputs[3] = "Last First Married Birth Date: (Work): MI  Single Child Driver's License: Ext: Other (Cell): State:";
        outputs[4] = "A.I am of sound mind and of legal age to make this Last W. B. This Last Will expresses my wishes without undue influence or duress. C. At the time of executing this Last Will: (Check one) I am married to I am NOT married.";
        outputs[5] = "DEPARTMENT CONTACT PERSON CONTACT PHONE CONTACT EMAIL DATE OF ORDER DATE APPROVED DATE RECEIVED";
        outputs[6] = "FULL NAME: ADDRESS: EMPLOYMENT / JOB APPLICATION First Street Address PERSONAL INFORMATION Middle Last DATE: AptSuite";
        outputs[7] = "INCOME TYPES INCOME ONE INCOME TWO PASSIVE INCOME SIDE HUSTLE INCOME DATE EXPECTED AMOUNT ACTUAL AMOUNT";
        outputs[8] = "VIRGINIA WILL INSTRUCTIONS Single with No Children U.s. Legal Forms, Inc. http://www.uslegalforms.com";
        outputs[9] = "Childhood illness: Immunizations and dates: Measles PERSONAL HEALTH HISTORY Mumps Rubella Tetanus Hepatitis Influenza Chickenpox Rheumatic Fever Pneumonia Chickenpox MMR Measles, Mumps Rubella Polio";
        outputs[10] = "On a scale of 1-5, how would you rate the diagnosis? Rate the effectiveness of the treatment and medication: 1 2 3 4 5";
        outputs[11] = "Table Reservations Time Party Size Name Phone Date: Table # Notes";
        outputs[12] = "3. I believe the person(s) I am suing is/are at least 18 years old and not in the military service. Defendant #1 date of birth Defendant #2 date of birth 4. I understand that if I do not come to court on my hearing date, my case will be dismissed and I may have to pa money to the Defendant(s) on any counterclaim that has been filed.";
        outputs[13] = "Thinking about yourself, how often would you say you are able to ... (put a cross in one box on each lime) .take responsibility? ... take advantage of an opportunity when you see one? most of the time some of the time not very often never";
        outputs[14] = "Full Name: Company: Address: References Relationship: Phone:";
        outputs[15] = "Please rate your satisfaction with the following Product quality Product Very Good Good Fair Poor Very Poor";
        outputs[16] = "Are you authorized to work in the U.S.? Have you ever been convicted of a felony? YES YES NO NO 1";
        outputs[17] = "Disclaimer and Signature I certify that my answers are true and complete to the best of my knowledge. If this application leads to employment, I understand that false or misleading information in my application or interview may result in my release. Signature: Date:";
        outputs[18] = "Company: Address: Job Title: Starting Salary:$ Phone: Supervisor: Ending Salary:$";
        outputs[19] = "Signature.:";
        outputs[20] = "Branch: Rank at Discharge: Military Service From: Type of Discharge: To:";
    }

    public static String getOutput(int i) {
        return i > 0 && i <= outputs.length ? outputs[i - 1] : null;
    }
}
