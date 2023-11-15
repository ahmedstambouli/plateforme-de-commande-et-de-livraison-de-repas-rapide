import { Component , OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AppService } from '../app.service';

@Component({
  selector: 'app-sing-in',
  templateUrl: './sing-in.component.html',
  styleUrls: ['./sing-in.component.css']
})
export class SingInComponent implements OnInit {
  registerForm!: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder,private service:AppService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      Email: ['', Validators.required],
      Password: ['', Validators.required],
    });
  }

  onSubmit() {
    //this.submitted = true;
    /*if (this.registerForm.invalid) {
      return;
    }*/

    console.log(this.registerForm.value.Email);
    console.log(this.registerForm.value.Password);


    const formData = new FormData();
    formData.append('email',this.registerForm.value.Email);
    formData.append('password',this.registerForm.value.Password);

    console.log(formData.get.name)
    this.service.loginPartenaire(formData).subscribe({
      next:(data)=>{
        console.log(data)

        },
        error:(err:any)=>
        {
          console.log(err)
        }

    })


    alert('Success');
  }

}
