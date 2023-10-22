import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppService } from '../app.service';
import { Partenaire } from '../Partenaire/Partenaire';

@Component({
  selector: 'app-sing-up',
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.css']
})
export class SingUpComponent implements OnInit {
  registerForm!: FormGroup;
  submitted = false;



  constructor(private formBuilder: FormBuilder,private appserver:AppService ) { }
  data:any;
  parte:Partenaire=new Partenaire()


  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      Name: ['', Validators.required],
      Email: ['', Validators.required],
      Phone: ['', Validators.required ],
      Adresse: ['', Validators.required],
      Password: ['', Validators.required],
      Role:[''],
    });


  }

  onSubmit() {



    this.submitted = true;
    //if (this.registerForm.invalid) {
    //  return;
   // }
   // alert('Success');
    /*this.appserver.savePartenaire().subscribe({
      next:(res :any)=>{
        console.log(res)
      },
      error:(err:any)=> {}
    });*/

    this.parte.name=this.registerForm.value.Name;
    this.parte.email=this.registerForm.value.Email;
    this.parte.tel=this.registerForm.value.Phone;
    this.parte.adresse=this.registerForm.value.Adresse;
    this.parte.password=this.registerForm.value.Password;
    this.parte.role="partenaire";
    console.log(this.parte)

    /*this.appserver.getAllPartenaire().subscribe({
      next:(res :any)=>{
        console.log(res,"response")
      },
      error:(err:any)=>{
        console.log(err,'errors')
      }
    })*/

    this.appserver.savePartenaire(this.parte).subscribe({
      next:(res:any)=>{
        console.log(res,"response")
      },
      error:(err:any)=>{
        console.log(err,'errors')
      }
    })

    //console.log(this.data);
  }
}
