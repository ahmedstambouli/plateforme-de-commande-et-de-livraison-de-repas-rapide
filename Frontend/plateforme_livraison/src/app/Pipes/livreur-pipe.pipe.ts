import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'livreurPipe'
})
export class LivreurPipePipe implements PipeTransform {
  transform(users: any[], searchText: string): any[] {
    if (!users || !searchText) {
      return users; // Return the original array if there's no search query or data
    }

    searchText = searchText.toLowerCase(); // Convert search query to lowercase for case-insensitive search

    return users.filter((user) => {
      // Perform a case-insensitive search by checking if the user's name or any other field contains the search query
      return user.name.toLowerCase().includes(searchText) ||
             user.address.toLowerCase().includes(searchText) ||
             user.email.toLowerCase().includes(searchText);
    });
  }
}
