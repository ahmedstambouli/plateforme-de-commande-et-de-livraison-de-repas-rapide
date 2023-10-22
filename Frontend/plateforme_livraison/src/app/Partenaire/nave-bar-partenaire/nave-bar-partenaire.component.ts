import { Component } from '@angular/core';

@Component({
  selector: 'app-nave-bar-partenaire',
  templateUrl: './nave-bar-partenaire.component.html',
  styleUrls: ['./nave-bar-partenaire.component.css']
})
export class NaveBarPartenaireComponent {
  public check =true
  public toggle(){
    if(this.check==true){
      this.check=false
    }
    else{
      this.check=true
    }

  }
}
