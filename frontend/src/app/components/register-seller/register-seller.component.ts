import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Seller } from 'src/app/Interfaces/Seller';
import { SelerWithId } from 'src/app/Interfaces/SelerWithId';
import { SellerService } from 'src/app/services/seller.service';

@Component({
    selector: 'app-register-seller',
    templateUrl: './register-seller.component.html',
    styleUrls: ['./register-seller.component.css']
})
export class RegisterSellerComponent implements OnChanges {

    constructor(private sellerService: SellerService) { } // Injects the service

    seller: Seller = {} as Seller; // Empty seller

    @Input()
    updatedSeller: SelerWithId = {} as SelerWithId; // Empty Seller with an ID


    // FormGroup Object containing a list of FormControls
    sellerForm = new FormGroup({
        id: new FormControl(''),
        name: new FormControl(''),
        salary: new FormControl(''),
        bonus: new FormControl(''),
        gender: new FormControl('')
    });


    // When the user clicks on the save button this method is called
    public save(): void {

        // It checks if the seller has an ID
        if (this.sellerForm.value.id) {

            // If it has an ID then UPDATES
            this.updateSeller();
            // alert("UPDATE");

        } else {

            // If does not have an ID then SAVES
            this.saveSeller();
            // alert("SAVE");

        }

        // Clears the form
        this.resetForm();

    }


    // Methods that gets called when trying to save a seller
    public saveSeller(): void {

        // Assigns the values from the form to the Seller attribute
        Object.assign(this.updatedSeller, this.sellerForm.value);

        // Calls the service
        this.sellerService.save(this.seller).subscribe(
            {
                next: () => (alert("The seller was registered!"))
            }
        );

    }


    // Methods that gets called when trying to update a seller
    public updateSeller(): void {

        // Assigns the values from the form to the updatedSeller attribute
        Object.assign(this.updatedSeller, this.sellerForm.value);


        // Calls the service
        this.sellerService.updateSeller(this.updatedSeller).subscribe(
            {
                next: () => (alert("Seller UPDATED"))
            }
        )

    }

    // Method to clear the formGroup
    public resetForm(): void {

        // Resets the formGroup
        this.sellerForm.reset();

        // Clears all attributes
        this.seller = {} as Seller;
        this.updatedSeller = {} as SelerWithId;
    }


    // Every time a user is send to be edited this method is called
    // it sets the values of the formControls with the UpdatedSeller information
    ngOnChanges(): void {

        /*
            TODO There is a small error here, as it's my understanding the TS compiler is complaing that
            * the values from id, salary and bonus maybe undefined, so it cannot convert from undefined to string
            * and the conversion is needed or else an error is thrown. Still, the code compiles and runs, so I
            * decided to let it as it is, maybe it should be changed later...
        */

        this.sellerForm.setValue({
            id: this.updatedSeller.id.toString(),
            name: this.updatedSeller.name,
            salary: this.updatedSeller.salary.toString(),
            bonus: this.updatedSeller.bonus.toString(),
            gender: this.updatedSeller.gender
        });
    }

}
