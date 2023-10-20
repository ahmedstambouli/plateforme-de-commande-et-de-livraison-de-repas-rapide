import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-sing-up',
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.css']
})
export class SingUpComponent implements OnInit {
  registerForm!: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder,private http: HttpClient) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      LastName: ['', Validators.required],
      Email: ['', Validators.required],
      Phone: ['', Validators.required],
      Adresse: ['', Validators.required],
      Password: ['', Validators.required],
    });
    let obj={
      "name":"oussema",
      "email": "john.doe@sdddddddddddddddsldlsdzddddjjlkdjzsm",
      "password": "secretPassword",
      "address": "t",
      "role":"admin",
      "tel": "555-123-4567"
  }
this.http.post('http://localhost:8085/public/users/register',obj).subscribe(res=>{
  console.log("Success",res)
})
  }

  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    alert('Success');
  }
}
