import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SingInComponent } from './sing-in/sing-in.component';
import { SingUpComponent } from './sing-up/sing-up.component';
import { UpdatePartenaireComponent } from './Admin/Partner/update-partenaire/update-partenaire.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'singin',
    pathMatch: 'full'
  },

  { path: 'singin', component:SingInComponent},
  { path: 'signup',component:SingUpComponent},

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
