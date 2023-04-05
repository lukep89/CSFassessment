import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css'],
})
export class SearchReviewComponent {
  form!: FormGroup;
  charName?: string;

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.form = this.createForm();
  }

  createForm() {
    return this.fb.group({
      movieName: this.fb.control('', [
        Validators.required,
        Validators.minLength(2),
        Validators.pattern(/[\S]/),
      ]),
    });
  }

  search() {
    const movieName = this.form.value['movieName'];
    console.log(
      '[search] navigating to list/:movieName >>> movieName = ' + movieName
    );

    this.router.navigate(['/list', movieName]);
  }
}
