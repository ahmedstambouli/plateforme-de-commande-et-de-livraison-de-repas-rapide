import { Component , OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AppService } from '../app.service';
import { Partenaire } from '../Partenaire/Partenaire';
import { Toast, ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-sing-in',
  templateUrl: './sing-in.component.html',
  styleUrls: ['./sing-in.component.css']
})
export class SingInComponent implements OnInit {
  registerForm!: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder,private service:AppService,private toast:ToastrService) { }
  partenaire:Partenaire=new Partenaire();

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      Email: ['', Validators.required],
      Password: ['', Validators.required],
    });
  }

  onSubmit() {
    var obj:any
    const formData = new FormData();
    formData.append('email',this.registerForm.value.Email);
    formData.append('password',this.registerForm.value.Password);

    console.log(formData.get.name)
    this.service.loginPartenaire(formData).subscribe({
      next:(data)=>{
        obj=data
        console.log(obj.id)
        if(obj!=null){
          localStorage.setItem("partenaire",JSON.stringify(obj));
          window.location.href='/home'
        }
        },
        error:(err:any)=>
        {
          console.log(err)
          this.toast.error("check email or password")
        }

    })


  }

}
