import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchReviewComponent } from './components/search-review/search-review.component';
import { MovieReviewListComponent } from './components/movie-review-list/movie-review-list.component';
import { PostCommentComponent } from './components/post-comment/post-comment.component';

const routes: Routes = [
  { path: '', component: SearchReviewComponent },
  // { path: 'search/:movieName', component: MovieReviewListComponent },
  // { path: 'search/:movieName', component: SearchReviewComponent },
  { path: 'list/:movieName', component: MovieReviewListComponent },
  // { path: 'details/:charId', component: DetailsComponent },
  { path: 'comment/:movieName', component: PostCommentComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
