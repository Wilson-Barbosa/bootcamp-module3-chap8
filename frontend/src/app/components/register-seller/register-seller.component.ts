import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Seller } from 'src/app/Interfaces/Seller';
import { SelerWithId } from 'src/app/Interfaces/SelerWithId';
import { SellerService } from 'src/app/services/seller.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-register-seller',
    templateUrl: './register-seller.component.html',
    styleUrls: ['./register-seller.component.css']
})
export class RegisterSellerComponent implements OnChanges {

    constructor(private sellerService: SellerService, private modalService: NgbModal) {} // Injects the service

     // Empty seller
    seller: Seller = {} as Seller;

     // Empty Seller with an ID
    @Input()
    updatedSeller: SelerWithId = {} as SelerWithId;

    // Tracks whether or not save was clicked by the user
    saveWasClicked: boolean = false;


    @Output()
    clearFather = new EventEmitter();


    // FormGroup Object containing a list of FormControls
    sellerForm = new FormGroup({
        id: new FormControl(''),
        name: new FormControl('', [
            Validators.required,
            Validators.minLength(5),
            Validators.maxLength(255)
        ]),
        salary: new FormControl('', [
            Validators.required,
            Validators.min(0)
        ]),
        bonus: new FormControl('', [
            Validators.required,
            Validators.min(0),
            Validators.max(100)
        ]),
        gender: new FormControl('', [
            Validators.required
        ])
    });



    // Method that clears the updatedSeller inside seller-list component
    public clearSellerObjectOnFather(){
        // Emits the empty seller to the father
        this.clearFather.emit();
    }


    // When the user clicks on the save button this method is called
    public save(modal: any): void {

        // Turns the button click to true for validation
        this.saveWasClicked = true;

        // Checks if the form is valid
        if (this.sellerForm.valid) {

            // Checks if the seller has an ID
            if (this.sellerForm.value.id) {
                this.updateSeller(modal); // If it has an ID then UPDATES
            } else {
                this.saveSeller(modal); // If does not have an ID then SAVES
            }

            // Clears the form
            this.resetForm();

            // Resets the clicked button to reset validation
            this.saveWasClicked = false;
        }

        // Clears the seller Object on father component
        this.clearSellerObjectOnFather();

    }


    // Methods that gets called when trying to save a seller
    public saveSeller(modal: any): void {

        // Assigns the values from the form to the Seller attribute
        Object.assign(this.seller, this.sellerForm.value);

        // Calls the service
        this.sellerService.save(this.seller).subscribe(
            {
                next: () => (this.modalService.open(modal))
            }
        );

    }


    // Methods that gets called when trying to update a seller
    public updateSeller(modal: any): void {

        // Assigns the values from the form to the updatedSeller attribute
        Object.assign(this.updatedSeller, this.sellerForm.value);


        // Calls the service
        this.sellerService.updateSeller(this.updatedSeller).subscribe(
            {
                next: () => (this.modalService.open(modal))
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

        // Clears the seller Object on father component
        this.clearSellerObjectOnFather();
    }


    // Every time a user is send to be edited this method is called
    // it sets the values of the formControls with the UpdatedSeller information
    ngOnChanges(): void {

        /*
            TODO There is a small error here. As it's my understanding the TS compiler is complaing that
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




    // Getter methods to check if there is errors on the form
    get name(): any {
        return this.sellerForm.get('name');
    }

    get salary(): any {
        return this.sellerForm.get('salary');
    }

    get bonus(): any {
        return this.sellerForm.get('bonus');
    }

    get gender(): any {
        return this.sellerForm.get('gender');
    }
}
