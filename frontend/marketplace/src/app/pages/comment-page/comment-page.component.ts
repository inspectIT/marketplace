import {Component, OnInit} from "@angular/core";

@Component({
  selector: 'app-comment-page',
  templateUrl: './comment-page.component.html',
  styleUrls: ['./comment-page.component.scss']
})
export class CommentPageComponent implements OnInit {




  constructor() { }

  ngOnInit() {
    console.log("comment works")
  }

}
