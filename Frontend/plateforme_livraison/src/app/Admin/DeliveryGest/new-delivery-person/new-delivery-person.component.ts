import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, EventEmitter, Output } from '@angular/core';
import { ServiceLivreurService } from 'src/app/Service/service-livreur.service';
import { User } from 'src/app/Model/User';

@Component({
  selector: 'app-new-delivery-person',
  templateUrl: './new-delivery-person.component.html',
  styleUrls: ['./new-delivery-person.component.css']
})
export class NewDeliveryPersonComponent {
  user: User = new User();
  submitted = false;

  deliveryPersonForm!: FormGroup;
  @Output() deliveryPersonCreated: EventEmitter<void> = new EventEmitter<void>();

  constructor(private LivreurServ: ServiceLivreurService, private formBuilder: FormBuilder) {}

  ngOnInit() {
    this.deliveryPersonForm = this.formBuilder.group({
      name: ['', Validators.required],
      address: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      tel: [
        '',
        [
          Validators.required,
          Validators.pattern(/^[0-9]*$/),
          this.telLengthValidator(8) // Custom validator for tel length
        ]
      ],
      password: ['', Validators.required]
    });
  }

  // Custom validator function to check the length of the tel field
  telLengthValidator(length: number) {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const telValue = control.value;

      if (telValue && telValue.toString().length !== length) {
        return { 'telLength': { value: telValue } };
      }

      return null; // Tel length is valid
    };
  }

  createLivreur() {
    this.submitted = true;
  
    if (this.deliveryPersonForm.valid) {
      this.user.role = 'LIVREUR';
      this.user.etat = 1;
  
      this.LivreurServ.createNewLivreur(this.user).subscribe(
        (response) => {
          console.log('New delivery person created successfully:', response);
          this.deliveryPersonCreated.emit();
        },
        (error) => {
          console.error('Error creating delivery person:', error);
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }
  
}
